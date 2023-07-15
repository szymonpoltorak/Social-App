import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Component } from '@angular/core';
import { HomeService } from '@services/home/home.service';
import { of } from 'rxjs';
import { UserData } from '@interfaces/home/UserData';
import { HomeDialogService } from '@services/home/home-dialog.service';
import { HomeProfileComponent } from "@home/home-profile/home-profile.component";
import { HttpClientTestingModule } from "@angular/common/http/testing";
import { HomeComponent } from "@home/home.component";
import { HomePostsComponent } from "@home/home-posts/home-posts.component";
import { HomeFriendsComponent } from "@home/home-friends/home-friends.component";
import { PostButtonBarComponent } from "@home/post-button-bar/post-button-bar.component";
import { CommonModule } from "@angular/common";
import { HomeRoutingModule } from "@home/home-routing.module";
import { SharedHomeModule } from "@home/shared-home/shared-home.module";
import { MatListModule } from "@angular/material/list";
import { IconsModule } from "@icons/icons.module";

@Component({ selector: 'app-test', template: '' })
class TestComponent {
}

describe('HomeProfileComponent', () => {
    let component: HomeProfileComponent;
    let fixture: ComponentFixture<HomeProfileComponent>;
    let homeService: HomeService;
    let homeDialogService: HomeDialogService;

    beforeEach(async () => {
        await TestBed.configureTestingModule({
            declarations: [
                HomeComponent,
                HomeProfileComponent,
                HomePostsComponent,
                HomeFriendsComponent,
                PostButtonBarComponent,
            ],
            imports: [
                CommonModule,
                HomeRoutingModule,
                SharedHomeModule,
                MatListModule,
                IconsModule,
                HttpClientTestingModule
            ],
            providers: [
                HomeDialogService
            ]
        }).compileComponents();
    });

    beforeEach(() => {
        fixture = TestBed.createComponent(HomeProfileComponent);
        component = fixture.componentInstance;
        homeService = TestBed.inject(HomeService);
        homeDialogService = TestBed.inject(HomeDialogService);
        fixture.detectChanges();
    });

    afterEach(() => {
        fixture.destroy();
    });

    it('should create the component', () => {
        expect(component).toBeTruthy();
    });

    describe('ngOnInit', () => {
        it('should fetch user data on initialization', () => {
            const userData: UserData = {
                job: 'Software Engineer',
                fullName: 'John Doe',
                numOfFriends: 100,
                location: 'New York',
                twitter: 'john_doe',
                github: 'johndoe',
                linkedin: 'johndoe'
            };
            spyOn(homeService, 'getUserData').and.returnValue(of(userData));

            component.ngOnInit();

            expect(component.job).toBe(userData.job);
            expect(component.fullName).toBe(userData.fullName);
            expect(component.numOfFriends).toBe(userData.numOfFriends);
            expect(component.userLocation).toBe(userData.location);
            expect(component.twitter).toBe(userData.twitter);
            expect(component.github).toBe(userData.github);
            expect(component.linkedin).toBe(userData.linkedin);
        });
    });

    describe('openDialog', () => {
        it('should open a dialog and update the corresponding property based on the dialog title', () => {
            spyOn(homeDialogService, 'openMatDialogWindow').and.returnValue(of('Updated Job'));

            component.openDialog('Job Dialog', '');

            expect(homeDialogService.openMatDialogWindow).toHaveBeenCalled();
            expect(component.job).toBe('');
        });

        it('should close the dialog if data is null', () => {
            spyOn(homeDialogService, 'openMatDialogWindow').and.returnValue(of(""));
            spyOn(homeDialogService, 'closeDialog');

            component.openDialog('Job Dialog', '');

            expect(homeDialogService.openMatDialogWindow).toHaveBeenCalled();
            expect(homeDialogService.closeDialog).toHaveBeenCalled();
        });

        it('should close the dialog and update the corresponding property based on the dialog title', () => {
            spyOn(homeDialogService, 'openMatDialogWindow').and.returnValue(of('Updated Location'));
            spyOn(homeDialogService, 'closeDialog');

            component.openDialog('Location Dialog', '');

            expect(homeDialogService.openMatDialogWindow).toHaveBeenCalled();
            expect(homeDialogService.closeDialog).toHaveBeenCalled();
            expect(component.userLocation).toBe('');
        });
    });

    describe('ngOnDestroy', () => {
        it('should complete the subjects', () => {
            spyOn(component.userDataDestroy$, 'next');
            spyOn(component.userDataDestroy$, 'complete');
            spyOn(component.destroyDialog$, 'next');
            spyOn(component.destroyDialog$, 'complete');

            component.ngOnDestroy();

            expect(component.userDataDestroy$.next).toHaveBeenCalled();
            expect(component.userDataDestroy$.complete).toHaveBeenCalled();
            expect(component.destroyDialog$.next).toHaveBeenCalled();
            expect(component.destroyDialog$.complete).toHaveBeenCalled();
        });
    });
});
