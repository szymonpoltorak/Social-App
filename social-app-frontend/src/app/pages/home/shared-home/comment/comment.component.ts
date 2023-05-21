import { Component, Input, OnDestroy } from '@angular/core';
import { CommentData } from "@interfaces/home/CommentData";
import { CommentsService } from "@services/home/comments.service";
import { Subject, takeUntil } from "rxjs";
import { LikeResponse } from "@interfaces/home/LikeResponse";

@Component({
    selector: 'app-comment',
    templateUrl: './comment.component.html',
    styleUrls: ['./comment.component.scss']
})
export class CommentComponent implements OnDestroy {
    private likeComment$: Subject<void> = new Subject<void>();
    @Input() commentData !: CommentData;

    constructor(private commentsService: CommentsService) {
    }

    updateLikeState(): void {
        this.commentsService.updateCommentNumOfLikes(this.commentData.commentId)
            .pipe(takeUntil(this.likeComment$))
            .subscribe((data: LikeResponse): void => {
                console.log(data);

                this.commentData.isCommentLiked = data.isLiked;
                this.commentData.numOfLikes = data.numOfLikes;
            });
    }

    ngOnDestroy(): void {
        this.likeComment$.next();
        this.likeComment$.complete();
    }
}
