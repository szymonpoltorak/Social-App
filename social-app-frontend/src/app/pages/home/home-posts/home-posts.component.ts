import { Component, EventEmitter, OnDestroy, OnInit, Output, ViewChild } from '@angular/core';
import { PostData } from "@core/interfaces/home/PostData";
import { PostService } from "@core/services/home/post.service";
import { Subject, takeUntil } from "rxjs";
import { UtilService } from "@services/utils/util.service";
import { StorageKeys } from "@core/enums/StorageKeys";
import { TextInputComponent } from "@home/shared-home/text-input/text-input.component";
import { HomePostsInterface } from "@interfaces/home/HomePostsInterface";

@Component({
    selector: 'app-home-posts',
    templateUrl: './home-posts.component.html',
    styleUrls: ['./home-posts.component.scss']
})
export class HomePostsComponent implements OnInit, OnDestroy, HomePostsInterface {
    destroyPostList$: Subject<void> = new Subject<void>();
    destroyCreatePost$: Subject<void> = new Subject<void>();
    loadMorePosts$: Subject<void> = new Subject<void>();
    numOfSite: number = 0;
    @Output() updateFriendListEvent: EventEmitter<void> = new EventEmitter<void>();
    @ViewChild(TextInputComponent) postTextInput !: TextInputComponent;
    posts: PostData[] = [];
    currentUser!: string;
    isAllLoaded !: boolean;

    constructor(private postService: PostService,
                private utilService: UtilService) {
    }

    loadNewPosts(): void {
        this.postService.getListOfPosts(this.numOfSite)
            .pipe(takeUntil(this.loadMorePosts$))
            .subscribe((data: PostData[]): void => {
                for (let postData of data) {
                    this.posts.push(postData);
                }

                if (data.length === 100) {
                    this.numOfSite++;
                } else {
                    this.isAllLoaded = true;
                }
            });
    }

    createNewPost(): void {
        this.postService.createNewPost(this.postTextInput.inputText)
            .pipe(takeUntil(this.destroyCreatePost$))
            .subscribe((data: PostData): void => {
                this.posts.unshift(data);
            });
        this.postTextInput.inputText = "";
        this.postTextInput.numOfCharacters = 0;
    }

    deletePostFromList(postData: PostData): void {
        this.posts = this.posts.filter(post => post !== postData);
    }

    updateFriendList(): void {
        this.updateFriendListEvent.emit();
    }

    ngOnInit(): void {
        this.currentUser = this.utilService.getValueFromStorage(StorageKeys.USERNAME);
        this.currentUser = this.currentUser.substring(1, this.currentUser.length - 1);
        this.isAllLoaded = false;

        this.postService.getListOfPosts(this.numOfSite)
            .pipe(takeUntil(this.destroyPostList$))
            .subscribe((data: PostData[]): void => {
                this.posts = data;

                if (data.length === 100) {
                    this.numOfSite++;
                } else {
                    this.isAllLoaded = true;
                }
            });
    }

    ngOnDestroy(): void {
        this.destroyPostList$.next();
        this.destroyPostList$.complete();

        this.destroyCreatePost$.next();
        this.destroyCreatePost$.complete();

        this.loadMorePosts$.next();
        this.loadMorePosts$.complete();
    }
}
