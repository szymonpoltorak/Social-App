import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Component } from '@angular/core';
import { CommentListComponent } from './comment-list.component';
import { CommentsService } from '@services/home/comments.service';
import { of } from 'rxjs';
import { CommentData } from '@interfaces/home/CommentData';
import { CommonModule } from "@angular/common";
import { MatListModule } from "@angular/material/list";
import { IconsModule } from "@icons/icons.module";
import { HttpClientTestingModule } from "@angular/common/http/testing";
import { FriendComponent } from "@home/shared-home/friend/friend.component";
import { TextInputComponent } from "@home/shared-home/text-input/text-input.component";
import { PostComponent } from "@home/shared-home/post/post.component";
import { SocialLinkComponent } from "@home/shared-home/social-link/social-link.component";
import { EditDialogComponent } from "@home/shared-home/edit-dialog/edit-dialog.component";
import { CommentComponent } from "@home/shared-home/comment/comment.component";
import { SocialNavbarComponent } from "@home/shared-home/social-navbar/social-navbar.component";
import { FormsModule, ReactiveFormsModule } from "@angular/forms";

@Component({ selector: 'app-test', template: '' })
class TestComponent {
}

describe('CommentListComponent', () => {
    let component: CommentListComponent;
    let fixture: ComponentFixture<CommentListComponent>;
    let commentsService: CommentsService;

    beforeEach(async () => {
        await TestBed.configureTestingModule({
            declarations: [
                CommentListComponent,
                TestComponent,
                FriendComponent,
                TextInputComponent,
                PostComponent,
                SocialLinkComponent,
                EditDialogComponent,
                CommentComponent,
                SocialNavbarComponent
            ],
            imports: [
                CommonModule,
                IconsModule,
                FormsModule,
                ReactiveFormsModule,
                MatListModule,
                HttpClientTestingModule
            ],
            providers: [CommentsService]
        }).compileComponents();
    });

    beforeEach(() => {
        fixture = TestBed.createComponent(CommentListComponent);
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

    describe('ngOnInit', () => {
        it('should fetch the list of comments on initialization', () => {
            const postId = 1;
            const numOfSite = 0;
            const comments: CommentData[] = [
                {
                    commentAuthor: 'John Doe',
                    commentContent: 'Great comment!',
                    commentId: 1,
                    commentDate: new Date(),
                    isCommentLiked: false,
                    numOfLikes: 10
                },
                {
                    commentAuthor: 'Jane Smith',
                    commentContent: 'Nice post!',
                    commentId: 2,
                    commentDate: new Date(),
                    isCommentLiked: true,
                    numOfLikes: 5
                }
            ];
            spyOn(commentsService, 'getListOfComments').and.returnValue(of(comments));

            component.postId = postId;
            component.numOfSite = numOfSite;
            component.ngOnInit();

            expect(commentsService.getListOfComments).toHaveBeenCalledWith(postId, numOfSite);
            expect(component.comments).toEqual(comments);
        });
    });

    describe('createNewComment', () => {
        it('should create a new comment and add it to the comments list', () => {
            const postId = 1;
            const commentText = 'New comment';
            const comment: CommentData = {
                commentAuthor: 'John Doe',
                commentContent: commentText,
                commentId: 3,
                commentDate: new Date(),
                isCommentLiked: false,
                numOfLikes: 0
            };
            spyOn(commentsService, 'createComment').and.returnValue(of(comment));

            component.postId = postId;
            component.commentInput.inputText = commentText;
            component.createNewComment();

            expect(commentsService.createComment).toHaveBeenCalledWith(commentText, postId);
            expect(component.comments[0]).toEqual(comment);
        });
    });

    describe('ngOnDestroy', () => {
        it('should complete the subject', () => {
            spyOn(component.commentList$, 'next');
            spyOn(component.commentList$, 'complete');

            component.ngOnDestroy();

            expect(component.commentList$.next).toHaveBeenCalled();
            expect(component.commentList$.complete).toHaveBeenCalled();
        });
    });
});
