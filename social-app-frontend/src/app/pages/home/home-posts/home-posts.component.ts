import { Component, EventEmitter, OnDestroy, OnInit, Output, ViewChild } from '@angular/core';
import { PostData } from "@core/interfaces/home/PostData";
import { PostService } from "@core/services/home/post.service";
import { Subject, takeUntil } from "rxjs";
import { UtilService } from "@services/utils/util.service";
import { StorageKeys } from "@core/enums/StorageKeys";
import { TextInputComponent } from "@home/shared-home/text-input/text-input.component";

@Component({
    selector: 'app-home-posts',
    templateUrl: './home-posts.component.html',
    styleUrls: ['./home-posts.component.scss']
})
export class HomePostsComponent implements OnInit, OnDestroy {
    private destroyPostList$: Subject<void> = new Subject<void>();
    private destroyCreatePost$: Subject<void> = new Subject<void>();
    @Output() updateFriendListEvent: EventEmitter<void> = new EventEmitter<void>();
    @ViewChild(TextInputComponent) postTextInput !: TextInputComponent;
    posts: PostData[] = [];
    currentUser!: string;

    constructor(private postService: PostService,
                private utilService: UtilService) {
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

        this.postService.getListOfPosts()
            .pipe(takeUntil(this.destroyPostList$))
            .subscribe((data: PostData[]): void => {
                this.posts = data;

                if (data.length === 100) {
                    this.postService.incrementSiteNumber();
                }
            });
    }

    ngOnDestroy(): void {
        this.destroyPostList$.next();
        this.destroyPostList$.complete();
    }
}
