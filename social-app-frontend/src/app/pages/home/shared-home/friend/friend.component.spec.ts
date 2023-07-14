import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Component, EventEmitter } from '@angular/core';
import { By } from '@angular/platform-browser';
import { of, Subject } from 'rxjs';
import { FriendComponent } from './friend.component';
import { UtilService } from '@services/utils/util.service';
import { UserHomeDataService } from '@core/services/home/user-home-data.service';
import { HomeApiCalls } from '@core/enums/HomeApiCalls';
import { TextInputComponent } from "@home/shared-home/text-input/text-input.component";
import { PostComponent } from "@home/shared-home/post/post.component";
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
class TestComponent {}

describe('FriendComponent', () => {
    let component: FriendComponent;
    let fixture: ComponentFixture<FriendComponent>;
    let userDataService: jasmine.SpyObj<UserHomeDataService>;
    let utilService: jasmine.SpyObj<UtilService>;
    let friendRemovalEventSpy: jasmine.SpyObj<EventEmitter<string>>;

    beforeEach(async () => {
        const userDataServiceMock = jasmine.createSpyObj('UserHomeDataService', ['manageUsersFriendEndpoints']);
        userDataServiceMock.manageUsersFriendEndpoints.and.returnValue(of(null));
        const utilServiceMock = jasmine.createSpyObj('UtilService', [], ['navigate']);

        await TestBed.configureTestingModule({
            declarations: [
                FriendComponent,
                TextInputComponent,
                PostComponent,
                SocialLinkComponent,
                EditDialogComponent,
                CommentListComponent,
                CommentComponent,
                SocialNavbarComponent
            ],
            imports: [
                CommonModule,
                IconsModule,
                FormsModule,
                ReactiveFormsModule,
                MatListModule,
                SharedAuthModule,
            ],
            providers: [
                { provide: UtilService, useValue: utilServiceMock },
                { provide: UserHomeDataService, useValue: userDataServiceMock }
            ]
        }).compileComponents();

        userDataService = TestBed.inject(UserHomeDataService) as jasmine.SpyObj<UserHomeDataService>;
        utilService = TestBed.inject(UtilService) as jasmine.SpyObj<UtilService>;

        friendRemovalEventSpy = jasmine.createSpyObj<EventEmitter<string>>('EventEmitter', ['emit']);
    });

    beforeEach(() => {
        fixture = TestBed.createComponent(FriendComponent);
        component = fixture.componentInstance;

        component.friendRemoval = friendRemovalEventSpy;

        fixture.detectChanges();
    });

    afterEach(() => {
        fixture.destroy();
    });

    it('should create the component', () => {
        expect(component).toBeTruthy();
    });

    it('should emit friendRemoval event and update isUsersFriend when removeUserFromFriends method is called', () => {
        component.friendUsername = 'john@gmail.com';
        component.isUsersFriend = true;
        component.removeUserFromFriends();

        expect(userDataService.manageUsersFriendEndpoints).toHaveBeenCalledWith('john@gmail.com', HomeApiCalls.REMOVE_FRIEND);
        expect(component.isUsersFriend).toBeFalse();
        expect(friendRemovalEventSpy.emit).toHaveBeenCalledWith('john@gmail.com');
    });

    it('should update isUsersFriend when addUserToFriends method is called', () => {
        component.friendUsername = 'john@gmail.com';
        component.isUsersFriend = false;
        component.addUserToFriends();

        expect(userDataService.manageUsersFriendEndpoints).toHaveBeenCalledWith('john@gmail.com', HomeApiCalls.ADD_FRIEND);
        expect(component.isUsersFriend).toBeTrue();
        expect(friendRemovalEventSpy.emit).not.toHaveBeenCalled();
    });

    it('should complete the destroyFriend$ subject when component is destroyed', () => {
        const destroyFriendSubject: Subject<void> = (component as any).destroyFriend$;
        spyOn(destroyFriendSubject, 'next');
        spyOn(destroyFriendSubject, 'complete');

        component.ngOnDestroy();

        expect(destroyFriendSubject.next).toHaveBeenCalled();
        expect(destroyFriendSubject.complete).toHaveBeenCalled();
    });
});
