import { Component, OnDestroy, OnInit, ViewChild } from '@angular/core';
import { PostData } from "@core/interfaces/home/PostData";
import { PostService } from "@core/services/post.service";
import { Subject, takeUntil } from "rxjs";
import { UtilService } from "@core/services/util.service";
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
    @ViewChild(TextInputComponent) postTextInput !: TextInputComponent;
    posts: PostData[] = [];
    currentUser!: string;

    constructor(private postService: PostService,
                private utilService: UtilService) {
    }

    createNewPost(): void {
        this.postService.createNewPost(this.postTextInput.postText, this.currentUser)
            .pipe(takeUntil(this.destroyCreatePost$))
            .subscribe((data: PostData): void => {
                this.posts.unshift(data);
            });
        this.postTextInput.postText = "";
        this.postTextInput.numOfCharacters = 0;
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
