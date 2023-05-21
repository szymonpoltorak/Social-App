import { Component, Input, OnDestroy, OnInit } from '@angular/core';
import { CommentData } from "@interfaces/home/CommentData";
import { CommentsService } from "@services/home/comments.service";
import { Subject, takeUntil } from "rxjs";

@Component({
  selector: 'app-comment-list',
  templateUrl: './comment-list.component.html',
  styleUrls: ['./comment-list.component.scss']
})
export class CommentListComponent implements OnInit, OnDestroy {
    private commentList$: Subject<void> = new Subject<void>();
    @Input() postId !: number;
    comments !: CommentData[];
    numOfSite: number = 0;

    constructor(private commentService: CommentsService) {
    }

    ngOnInit(): void {
        this.commentService.getListOfComments(this.postId, this.numOfSite)
            .pipe(takeUntil(this.commentList$))
            .subscribe((data: CommentData[]): void => {
                console.log(data);

                this.comments = data;
            });
    }

    ngOnDestroy(): void {
        this.commentList$.next();
        this.commentList$.complete();
    }
}
