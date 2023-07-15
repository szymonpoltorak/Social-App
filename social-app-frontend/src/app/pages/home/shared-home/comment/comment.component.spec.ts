import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Component } from '@angular/core';
import { CommentComponent } from './comment.component';
import { of } from 'rxjs';
import { CommentsService } from '@services/home/comments.service';
import { CommentData } from '@interfaces/home/CommentData';
import { LikeResponse } from '@interfaces/home/LikeResponse';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { IconsModule } from "@icons/icons.module";

@Component({ selector: 'app-test', template: '' })
class TestComponent {
}

describe('CommentComponent', () => {
    let component: CommentComponent;
    let fixture: ComponentFixture<CommentComponent>;
    let commentsService: CommentsService;

    beforeEach(async () => {
        await TestBed.configureTestingModule({
            declarations: [CommentComponent, TestComponent],
            imports: [HttpClientTestingModule, IconsModule],
            providers: [CommentsService],
        }).compileComponents();
    });

    beforeEach(() => {
        fixture = TestBed.createComponent(CommentComponent);
        component = fixture.componentInstance;
        commentsService = TestBed.inject(CommentsService);
        fixture.detectChanges();
    });

    afterEach(() => {
        fixture.destroy();
    });

    it('should create the component', () => {
        expect(component).toBeTruthy();
    });

    describe('updateLikeState', () => {
        it('should update the like state and number of likes of the comment', () => {
            const commentData: CommentData = {
                commentAuthor: 'John Doe',
                commentContent: 'Great comment!',
                commentId: 1,
                commentDate: new Date(),
                isCommentLiked: false,
                numOfLikes: 10,
            };
            const likeResponse: LikeResponse = {
                isLiked: true,
                numOfLikes: 11,
            };
            spyOn(commentsService, 'updateCommentNumOfLikes').and.returnValue(of(likeResponse));

            component.commentData = commentData;
            component.updateLikeState();

            expect(commentsService.updateCommentNumOfLikes).toHaveBeenCalledWith(commentData.commentId);
            expect(component.commentData.isCommentLiked).toBe(likeResponse.isLiked);
            expect(component.commentData.numOfLikes).toBe(likeResponse.numOfLikes);
        });
    });

    describe('ngOnDestroy', () => {
        it('should complete the subject', () => {
            component.commentData = {
                commentAuthor: 'John Doe',
                commentContent: 'Great comment!',
                commentId: 1,
                commentDate: new Date(),
                isCommentLiked: false,
                numOfLikes: 0,
            };

            spyOn(component.likeComment$, 'next');
            spyOn(component.likeComment$, 'complete');

            component.ngOnDestroy();

            expect(component.likeComment$.next).toHaveBeenCalled();
            expect(component.likeComment$.complete).toHaveBeenCalled();
        });
    });
});
