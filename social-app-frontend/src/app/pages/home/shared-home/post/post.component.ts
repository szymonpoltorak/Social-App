import { Component, Input, OnDestroy } from '@angular/core';
import { PostService } from "@core/services/post.service";
import { Subject, takeUntil } from "rxjs";
import { HomeApiCalls } from "@core/enums/HomeApiCalls";
import { PostData } from "@core/interfaces/home/PostData";

@Component({
    selector: 'app-post',
    templateUrl: './post.component.html',
    styleUrls: ['./post.component.scss']
})
export class PostComponent implements OnDestroy {
    private addFriend$: Subject<void> = new Subject<void>();
    private removeFriend$: Subject<void> = new Subject<void>();
    private updateLike$: Subject<void> = new Subject<void>();
    @Input() postData !: PostData;
    @Input() currentUser !: string;
    isPostLiked!: boolean;
    isFriendAdded!: boolean;

    constructor(private postService: PostService) {
    }

    updatePostLike(): void {
        this.isPostLiked = !this.isPostLiked;
        this.postData.numOfLikes = this.isPostLiked ? this.postData.numOfLikes + 1 : this.postData.numOfLikes - 1;

        this.postService.updateNumOfLikes(this.postData.postId, this.postData.numOfLikes)
            .pipe(takeUntil(this.updateLike$))
            .subscribe();
    }

    updateFriendStatus(): void {
        if (!this.isFriendAdded) {
            this.postService.manageFriendStatus(this.currentUser, this.postData.username, HomeApiCalls.ADD_FRIEND)
                .pipe(takeUntil(this.addFriend$))
                .subscribe((): void => {
                    this.isFriendAdded = true;
                });
        } else {
            this.postService.manageFriendStatus(this.currentUser, this.postData.username, HomeApiCalls.REMOVE_FRIEND)
                .pipe(takeUntil(this.removeFriend$))
                .subscribe((): void => {
                    this.isFriendAdded = false;
                });
        }
    }

    ngOnDestroy(): void {
        this.addFriend$.next();
        this.addFriend$.complete();

        this.removeFriend$.next();
        this.removeFriend$.complete();

        this.updateLike$.next();
        this.updateLike$.complete();
    }
}
