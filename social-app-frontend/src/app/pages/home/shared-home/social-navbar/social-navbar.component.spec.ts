import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Component, Input, Output, EventEmitter } from '@angular/core';
import { By } from '@angular/platform-browser';
import { of, Subject } from 'rxjs';
import { SocialNavbarComponent } from './social-navbar.component';
import { AuthService } from '@core/services/auth/auth.service';
import { UtilService } from '@services/utils/util.service';
import { UserService } from '@services/utils/user.service';
import { RoutePaths } from '@core/enums/RoutePaths';
import { SearchService } from '@services/search/search.service';
import { SocialNavbarInterface } from '@interfaces/home/SocialNavbarInterface';
import { ColumnIndex } from '@enums/ColumnIndex';
import { FriendComponent } from "@home/shared-home/friend/friend.component";
import { TextInputComponent } from "@home/shared-home/text-input/text-input.component";
import { PostComponent } from "@home/shared-home/post/post.component";
import { SocialLinkComponent } from "@home/shared-home/social-link/social-link.component";
import { EditDialogComponent } from "@home/shared-home/edit-dialog/edit-dialog.component";
import { CommentListComponent } from "@home/shared-home/comment-list/comment-list.component";
import { CommentComponent } from "@home/shared-home/comment/comment.component";
import { CommonModule } from "@angular/common";
import { IconsModule } from "@icons/icons.module";
import { FormsModule, ReactiveFormsModule } from "@angular/forms";
import { MatListModule } from "@angular/material/list";
import { SharedAuthModule } from "@auth/shared/shared-auth.module";

@Component({ selector: 'app-test', template: '' })
class TestComponent {}

describe('SocialNavbarComponent', () => {
    let component: SocialNavbarComponent;
    let fixture: ComponentFixture<SocialNavbarComponent>;
    let authService: jasmine.SpyObj<AuthService>;
    let utilService: jasmine.SpyObj<UtilService>;
    let userService: jasmine.SpyObj<UserService>;
    let searchService: jasmine.SpyObj<SearchService>;

    beforeEach(async () => {
        const authServiceMock = jasmine.createSpyObj('AuthService', ['logoutUser']);
        const utilServiceMock = jasmine.createSpyObj('UtilService', ['clearStorage', 'navigate']);
        const userServiceMock = jasmine.createSpyObj('UserService', ['setWasUserLoggedOut']);
        const searchServiceMock = jasmine.createSpyObj('SearchService', ['searchPattern']);

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
            providers: [
                { provide: AuthService, useValue: authServiceMock },
                { provide: UtilService, useValue: utilServiceMock },
                { provide: UserService, useValue: userServiceMock },
                { provide: SearchService, useValue: searchServiceMock },
            ],
        }).compileComponents();
    });

    beforeEach(() => {
        fixture = TestBed.createComponent(SocialNavbarComponent);
        component = fixture.componentInstance;
        authService = TestBed.inject(AuthService) as jasmine.SpyObj<AuthService>;
        utilService = TestBed.inject(UtilService) as jasmine.SpyObj<UtilService>;
        userService = TestBed.inject(UserService) as jasmine.SpyObj<UserService>;
        searchService = TestBed.inject(SearchService) as jasmine.SpyObj<SearchService>;
        fixture.detectChanges();
    });

    afterEach(() => {
        fixture.destroy();
    });

    it('should create the component', () => {
        expect(component).toBeTruthy();
    });

    it('should toggle the menu visibility correctly', () => {
        component.isMenuVisible = true;
        component.toggleMenu();
        expect(component.isMenuVisible).toBeFalse();

        component.isMenuVisible = false;
        component.toggleMenu();
        expect(component.isMenuVisible).toBeTrue();
    });

    it('should update the isOneColumnOnly property on window resize', () => {
        component.isOneColumnOnly = false;
        component.currentColumn = 1;

        // Simulate window resize with innerWidth less than or equal to 800
        (window.innerWidth as any) = 800;
        window.dispatchEvent(new Event('resize'));
        expect(component.isOneColumnOnly).toBeTrue();
        expect(component.currentColumn).toBe(0);

        // Simulate window resize with innerWidth greater than 800
        (window.innerWidth as any) = 1024;
        window.dispatchEvent(new Event('resize'));
        expect(component.isOneColumnOnly).toBeFalse();
        expect(component.currentColumn).toBe(1);
    });

    it('should change the current column correctly', () => {
        component.currentColumn = 0;
        const columnEventSpy = spyOn(component.columnEvent, 'emit');

        component.changeColumn(1);
        expect(component.currentColumn).toBe(1);
        expect(columnEventSpy).toHaveBeenCalledWith(1);
    });

    it('should logout the user and navigate to the login page', () => {
        authService.logoutUser.and.returnValue(of(null));
        const clearStorageSpy = spyOn(utilService, 'clearStorage').and.stub();
        userService.setWasUserLoggedOut = true;
        const navigateSpy = spyOn(utilService, 'navigate').and.stub();

        component.logoutUserFromSite();

        expect(authService.logoutUser).toHaveBeenCalled();
        expect(clearStorageSpy).toHaveBeenCalled();
        expect(userService.setWasUserLoggedOut).toBe(true);
        expect(navigateSpy).toHaveBeenCalledWith(RoutePaths.LOGIN_DIRECT);
    });


    it('should search for a user and emit the search event', () => {
        component.searchValue = 'John';
        const searchEventSpy = spyOn(component.searchEvent, 'emit');

        component.searchForUser();

        expect(searchService.searchPattern).toBe('John');
        expect(searchEventSpy).toHaveBeenCalled();
    });

    it('should navigate to the URL when logoUrl is provided', () => {
        component.logoUrl = 'https://example.com';
        const navigateSpy = utilService.navigate.and.stub();

        component.navigateToUrl();

        expect(navigateSpy).toHaveBeenCalledWith('https://example.com');
    });

    it('should not navigate when logoUrl is empty', () => {
        const navigateSpy = utilService.navigate.and.stub();

        component.navigateToUrl();

        expect(navigateSpy).not.toHaveBeenCalled();
    });

    it('should complete the onDestroy$ subject when component is destroyed', () => {
        const onDestroySubject: Subject<void> = (component as any).onDestroy$;
        spyOn(onDestroySubject, 'next');
        spyOn(onDestroySubject, 'complete');

        component.ngOnDestroy();

        expect(onDestroySubject.next).toHaveBeenCalled();
        expect(onDestroySubject.complete).toHaveBeenCalled();
    });

    it('should expose the ColumnIndex enum', () => {
        expect((component as any).ColumnIndex).toBe(ColumnIndex);
    });
});
