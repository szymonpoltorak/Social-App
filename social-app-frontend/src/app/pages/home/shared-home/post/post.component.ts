import { Component, Input, OnDestroy, OnInit } from '@angular/core';
import { PostService } from "@core/services/post.service";
import { Subject, takeUntil } from "rxjs";
import { HomeApiCalls } from "@core/enums/HomeApiCalls";

@Component({
    selector: 'app-post',
    templateUrl: './post.component.html',
    styleUrls: ['./post.component.scss']
})
export class PostComponent implements OnInit, OnDestroy {
    private addFriend$: Subject<void> = new Subject<void>();
    private removeFriend$: Subject<void> = new Subject<void>();
    @Input() postAuthor!: string;
    @Input() postContent!: string;
    @Input() postDate !: Date;
    @Input() numOfLikes!: number;
    @Input() numOfComments!: number;
    @Input() postId !: number;
    @Input() username !: string;
    isPostLiked!: boolean;
    isFriendAdded!: boolean;
    @Input() currentUser !: string;

    constructor(private postService: PostService) {
    }

    updatePostLike(): void {
        this.isPostLiked = !this.isPostLiked;
        this.numOfLikes = this.isPostLiked ? this.numOfLikes + 1 : this.numOfLikes - 1;
    }

    updateFriendStatus(): void {
        if (!this.isFriendAdded) {
            this.postService.manageFriendStatus(this.currentUser, this.username, HomeApiCalls.ADD_FRIEND)
                .pipe(takeUntil(this.addFriend$))
                .subscribe((): void => {
                    this.isFriendAdded = true;
                });
        } else {
            this.postService.manageFriendStatus(this.currentUser, this.username, HomeApiCalls.REMOVE_FRIEND)
                .pipe(takeUntil(this.removeFriend$))
                .subscribe((): void => {
                    this.isFriendAdded = false;
                });
        }
    }

    ngOnInit(): void {
    }

    ngOnDestroy(): void {
        this.addFriend$.next();
        this.addFriend$.complete();
    }
}
