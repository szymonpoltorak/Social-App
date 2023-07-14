import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HomeComponent } from '@home/home.component';
import { HomeService } from '@services/home/home.service';
import { UtilService } from '@services/utils/util.service';
import { FriendData } from '@interfaces/home/FriendData';
import { RoutePaths } from '@enums/RoutePaths';
import { ColumnIndex } from '@enums/ColumnIndex';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { SharedHomeModule } from "@home/shared-home/shared-home.module";
import { HomeProfileComponent } from "@home/home-profile/home-profile.component";
import { IconsModule } from "@icons/icons.module";
import { HomePostsComponent } from "@home/home-posts/home-posts.component";
import { HomeFriendsComponent } from "@home/home-friends/home-friends.component";
import { PostButtonBarComponent } from "@home/post-button-bar/post-button-bar.component";
import { CommonModule } from "@angular/common";
import { HomeRoutingModule } from "@home/home-routing.module";
import { MatListModule } from "@angular/material/list";
import { HomeDialogService } from "@services/home/home-dialog.service";

describe('HomeComponent', () => {
    let component: HomeComponent;
    let fixture: ComponentFixture<HomeComponent>;
    let homeService: HomeService;
    let utilService: UtilService;

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
        fixture = TestBed.createComponent(HomeComponent);
        component = fixture.componentInstance;
        homeService = TestBed.inject(HomeService);
        utilService = TestBed.inject(UtilService);
        fixture.detectChanges();
    });

    afterEach(() => {
        fixture.destroy();
    });

    it('should create the component', () => {
        expect(component).toBeTruthy();
    });

    describe('ngOnInit', () => {
        it('should fetch friend list on initialization', () => {
            const friendListData: FriendData[] = [
                { friendUsername: 'john@gmail.com', friendFullName: 'John Smith', friendJob: '' },
                { friendUsername: 'jane@gmail.com', friendFullName: 'Jane Smith', friendJob: '' },
            ];
            spyOn(homeService, 'getFriendList').and.returnValue(of(friendListData));

            component.ngOnInit();

            expect(component.friendList).toEqual(friendListData);
            expect(homeService.getFriendList).toHaveBeenCalled();
        });
    });

    describe('updateFriendList', () => {
        it('should update friend list', () => {
            const friendListData: FriendData[] = [
                { friendUsername: 'john@gmail.com', friendFullName: 'John Smith', friendJob: '' },
                { friendUsername: 'jane@gmail.com', friendFullName: 'Jane Smith', friendJob: '' },
            ];
            spyOn(homeService, 'getFriendList').and.returnValue(of(friendListData));

            component.updateFriendList();

            expect(component.friendList).toEqual(friendListData);
            expect(homeService.getFriendList).toHaveBeenCalled();
        });
    });

    describe('navigateToSearch', () => {
        it('should navigate to search site path', () => {
            spyOn(utilService, 'navigate');

            component.navigateToSearch();

            expect(utilService.navigate).toHaveBeenCalledWith(RoutePaths.SEARCH_SITE_PATH);
        });
    });

    describe('hideColumn', () => {
        it('should hide/show friend column based on event', () => {
            component.areAllVisible = true;
            component.currentColumn = ColumnIndex.USER_COLUMN;
            component.areFriendsVisible = true;

            component.hideColumn(ColumnIndex.USER_COLUMN);

            expect(component.currentColumn).toBe(ColumnIndex.USER_COLUMN);
            expect(component.areFriendsVisible).toBe(false);

            component.hideColumn(ColumnIndex.POSTS_COLUMN);

            expect(component.currentColumn).toBe(ColumnIndex.POSTS_COLUMN);
            expect(component.areFriendsVisible).toBe(true);
        });
    });

    describe('onWindowResize', () => {
        it('should update properties based on window width', () => {
            component.isDoubleColumnGrid = true;
            component.currentColumn = ColumnIndex.POSTS_COLUMN;

            spyOnProperty(window, 'innerWidth').and.returnValue(1000);

            component.onWindowResize({});

            expect(component.areAllVisible).toBe(false);
            expect(component.isDoubleColumnGrid).toBe(true);
            expect(component.currentColumn).toBe(ColumnIndex.USER_COLUMN);
        });
    });

    describe('ngOnDestroy', () => {
        it('should complete the subjects', () => {
            spyOn(component.destroyFriendList$, 'next');
            spyOn(component.destroyFriendList$, 'complete');
            spyOn(component.updateFriendList$, 'next');
            spyOn(component.updateFriendList$, 'complete');

            component.ngOnDestroy();

            expect(component.destroyFriendList$.next).toHaveBeenCalled();
            expect(component.destroyFriendList$.complete).toHaveBeenCalled();
            expect(component.updateFriendList$.next).toHaveBeenCalled();
            expect(component.updateFriendList$.complete).toHaveBeenCalled();
        });
    });
});
