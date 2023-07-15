import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Component, EventEmitter } from '@angular/core';
import { of, Subject } from 'rxjs';
import { PostComponent } from './post.component';
import { PostService } from '@core/services/home/post.service';
import { PostData } from '@core/interfaces/home/PostData';
import { LikeResponse } from '@core/interfaces/home/LikeResponse';
import { FriendComponent } from "@home/shared-home/friend/friend.component";
import { TextInputComponent } from "@home/shared-home/text-input/text-input.component";
import { SocialLinkComponent } from "@home/shared-home/social-link/social-link.component";
import { EditDialogComponent } from "@home/shared-home/edit-dialog/edit-dialog.component";
import { CommentListComponent } from "@home/shared-home/comment-list/comment-list.component";
import { CommentComponent } from "@home/shared-home/comment/comment.component";
import { SocialNavbarComponent } from "@home/shared-home/social-navbar/social-navbar.component";
import { CommonModule } from "@angular/common";
import { IconsModule } from "@icons/icons.module";
import { FormsModule, ReactiveFormsModule } from "@angular/forms";
import { MatListModule } from "@angular/material/list";
import { SharedAuthModule } from "@auth/shared/shared-auth.module";

@Component({ selector: 'app-test', template: '' })
class TestComponent {
}

describe('PostComponent', () => {
    let component: PostComponent;
    let fixture: ComponentFixture<PostComponent>;
    let postService: jasmine.SpyObj<PostService>;
    let deleteEventSpy: jasmine.SpyObj<EventEmitter<PostData>>;
    let updateFriendListEventSpy: jasmine.SpyObj<EventEmitter<void>>;

    beforeEach(async () => {
        const postServiceMock = jasmine.createSpyObj('PostService', [
            'updateNumOfLikes',
            'manageFriendStatus',
            'deletePost'
        ]);

        await TestBed.configureTestingModule({
            declarations: [
                FriendComponent,
                TextInputComponent,
                PostComponent,
                SocialLinkComponent,
                EditDialogComponent,
                CommentListComponent,
                CommentComponent,
                SocialNavbarComponent,
                TestComponent
            ],
            imports: [
                CommonModule,
                IconsModule,
                FormsModule,
                ReactiveFormsModule,
                MatListModule,
                SharedAuthModule,
            ],
            providers: [{ provide: PostService, useValue: postServiceMock }],
        }).compileComponents();

        postService = TestBed.inject(PostService) as jasmine.SpyObj<PostService>;

        deleteEventSpy = jasmine.createSpyObj<EventEmitter<PostData>>('EventEmitter', ['emit']);
        updateFriendListEventSpy = jasmine.createSpyObj<EventEmitter<void>>('EventEmitter', ['emit']);
    });

    beforeEach(() => {
        fixture = TestBed.createComponent(PostComponent);
        component = fixture.componentInstance;

        component.deleteEvent = deleteEventSpy;
        component.updateFriendListEvent = updateFriendListEventSpy;

        fixture.detectChanges();
    });

    afterEach(() => {
        fixture.destroy();
    });

    it('should create the component', () => {
        expect(component).toBeTruthy();
    });

    it('should initialize postData when it is not provided', () => {
        expect(component.postData).toEqual({
            postId: 0,
            postContent: '',
            postDate: jasmine.any(Date),
            username: '',
            numOfLikes: 0,
            isPostLiked: false,
            isUserInFriends: false,
            numOfComments: 0,
            postAuthor: ''
        });
    });

    it('should call postService.updateNumOfLikes and update the postData when updatePostLike method is called', () => {
        const likeResponse: LikeResponse = {
            numOfLikes: 10,
            isLiked: true
        };
        postService.updateNumOfLikes.and.returnValue(of(likeResponse));

        component.postData = {
            postId: 1,
            postContent: 'Test post',
            postDate: new Date(),
            username: 'john',
            numOfLikes: 5,
            isPostLiked: false,
            isUserInFriends: true,
            numOfComments: 2,
            postAuthor: 'John Doe'
        };
        component.updatePostLike();

        expect(postService.updateNumOfLikes).toHaveBeenCalledWith(1);
        expect(component.postData.numOfLikes).toBe(10);
        expect(component.postData.isPostLiked).toBe(true);
    });

    it('should complete the subject when component is destroyed', () => {
        const destroySubject: Subject<void> = (component as any).addFriend$;
        spyOn(destroySubject, 'next');
        spyOn(destroySubject, 'complete');

        component.ngOnDestroy();

        expect(destroySubject.next).toHaveBeenCalled();
        expect(destroySubject.complete).toHaveBeenCalled();
    });
});
