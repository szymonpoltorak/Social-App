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

    constructor(private postService: PostService,
                private utilService: UtilService) {
    }

    createNewPost(): void {
        let username: string = this.utilService.getValueFromStorage(StorageKeys.USERNAME);

        username = username.substring(1, username.length - 1);

        this.postService.createNewPost(this.postTextInput.postText, username)
            .pipe(takeUntil(this.destroyCreatePost$))
            .subscribe((data: PostData): void => {
                console.log(data);

                this.posts.unshift(data);
            });
        this.postTextInput.postText = "";
        this.postTextInput.numOfCharacters = 0;
    }

    ngOnInit(): void {
        this.postService.getListOfPosts()
            .pipe(takeUntil(this.destroyPostList$))
            .subscribe((data: PostData[]): void => {
                console.log(data);

                this.posts = data;
            });
    }

    ngOnDestroy(): void {
        this.destroyPostList$.next();
        this.destroyPostList$.complete();
    }
}
