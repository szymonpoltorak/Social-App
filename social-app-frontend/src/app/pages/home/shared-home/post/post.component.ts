import { Component, EventEmitter, Input, OnDestroy, Output } from '@angular/core';
import { PostService } from "@core/services/post.service";
import { Subject, takeUntil } from "rxjs";
import { HomeApiCalls } from "@core/enums/HomeApiCalls";
import { PostData } from "@core/interfaces/home/PostData";
import { LikeResponse } from "@core/interfaces/home/LikeResponse";

@Component({
    selector: 'app-post',
    templateUrl: './post.component.html',
    styleUrls: ['./post.component.scss']
})
export class PostComponent implements OnDestroy {
    private addFriend$: Subject<void> = new Subject<void>();
    private removeFriend$: Subject<void> = new Subject<void>();
    private updateLike$: Subject<void> = new Subject<void>();
    private deletePost$: Subject<void> = new Subject<void>();
    @Output() deleteEvent: EventEmitter<PostData> = new EventEmitter<PostData>();
    @Output() updateFriendListEvent: EventEmitter<void> = new EventEmitter<void>();
    @Input() postData !: PostData;
    @Input() currentUser !: string;

    constructor(private postService: PostService) {
    }

    updatePostLike(): void {
        this.postService.updateNumOfLikes(this.postData.postId)
            .pipe(takeUntil(this.updateLike$))
            .subscribe((data: LikeResponse): void => {
                this.postData.numOfLikes = data.numOfLikes;
                this.postData.isPostLiked = data.isPostLiked;
            });
    }

    updateFriendStatus(): void {
        if (!this.postData.isUserInFriends) {
            this.postService.manageFriendStatus(this.postData.username, HomeApiCalls.ADD_FRIEND)
                .pipe(takeUntil(this.addFriend$))
                .subscribe((): void => {
                    this.postData.isUserInFriends = true;

                    this.updateFriendListEvent.emit();
                });
        } else {
            this.postService.manageFriendStatus(this.postData.username, HomeApiCalls.REMOVE_FRIEND)
                .pipe(takeUntil(this.removeFriend$))
                .subscribe((): void => {
                    this.postData.isUserInFriends = false;

                    this.updateFriendListEvent.emit();
                });
        }
    }

    removePost(): void {
        this.postService.deletePost(this.postData.postId)
            .pipe(takeUntil(this.deletePost$))
            .subscribe((): void => {
                this.deleteEvent.emit(this.postData);
            });
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
