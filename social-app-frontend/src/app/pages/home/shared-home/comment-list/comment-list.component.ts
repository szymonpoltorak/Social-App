import { Component, Input, OnDestroy, OnInit, ViewChild } from '@angular/core';
import { CommentData } from "@interfaces/home/CommentData";
import { CommentsService } from "@services/home/comments.service";
import { Subject, takeUntil } from "rxjs";
import { TextInputComponent } from "@home/shared-home/text-input/text-input.component";
import { CommentListInterface } from "@interfaces/home/CommentListInterface";

@Component({
  selector: 'app-comment-list',
  templateUrl: './comment-list.component.html',
  styleUrls: ['./comment-list.component.scss']
})
export class CommentListComponent implements OnInit, OnDestroy, CommentListInterface {
    private commentList$: Subject<void> = new Subject<void>();
    private createPost$: Subject<void> = new Subject<void>();
    @ViewChild(TextInputComponent) commentInput !: TextInputComponent;
    @Input() postId !: number;
    comments !: CommentData[];
    numOfSite: number = 0;

    constructor(private commentService: CommentsService) {
    }

    ngOnInit(): void {
        this.commentService.getListOfComments(this.postId, this.numOfSite)
            .pipe(takeUntil(this.commentList$))
            .subscribe((data: CommentData[]): void => {
                this.comments = data;
            });
    }

    createNewComment(): void {
        this.commentService.createComment(this.commentInput.inputText, this.postId)
            .pipe(takeUntil(this.createPost$))
            .subscribe((data: CommentData): void => {
               this.comments.unshift(data);

               this.commentInput.inputText = "";
               this.commentInput.numOfCharacters = 0;
            });
    }

    ngOnDestroy(): void {
        this.commentList$.next();
        this.commentList$.complete();
    }
}
