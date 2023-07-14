import { Component, HostListener, OnDestroy, OnInit } from '@angular/core';
import { HomeService } from "@core/services/home/home.service";
import { Subject, takeUntil } from "rxjs";
import { FriendData } from "@core/interfaces/home/FriendData";
import { UtilService } from "@services/utils/util.service";
import { RoutePaths } from "@enums/RoutePaths";
import { ColumnIndex } from "@enums/ColumnIndex";

@Component({
    selector: 'app-home',
    templateUrl: './home.component.html',
    styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit, OnDestroy {
    destroyFriendList$: Subject<void> = new Subject<void>();
    updateFriendList$: Subject<void> = new Subject<void>();
    friendList: FriendData[] = [];
    areFriendsVisible: boolean = false;
    areAllVisible: boolean = true;
    isDoubleColumnGrid: boolean = true;
    currentColumn: number = ColumnIndex.USER_COLUMN;

    constructor(private homeService: HomeService,
                private utilService: UtilService) {
    }

    hideColumn(event: number): void {
        this.areAllVisible = window.innerWidth >= 1250;

        this.currentColumn = event;
        this.areFriendsVisible = !this.areFriendsVisible;
    }

    @HostListener('window:resize', ['$event'])
    onWindowResize(event: any): void {
        this.areAllVisible = window.innerWidth >= 1250;
        this.isDoubleColumnGrid = window.innerWidth > 800;

        if (this.isDoubleColumnGrid && this.currentColumn === ColumnIndex.POSTS_COLUMN) {
            this.currentColumn = ColumnIndex.USER_COLUMN;
        }
    }

    ngOnInit(): void {
        this.homeService.getFriendList()
            .pipe(takeUntil(this.destroyFriendList$))
            .subscribe((data: FriendData[]): void => {
                this.friendList = data;
            });
    }

    updateFriendList(): void {
        this.homeService.getFriendList()
            .pipe(takeUntil(this.updateFriendList$))
            .subscribe((data: FriendData[]): void => {
                this.friendList = data;
            });
    }

    navigateToSearch(): void {
        this.utilService.navigate(RoutePaths.SEARCH_SITE_PATH);
    }

    ngOnDestroy(): void {
        this.destroyFriendList$.next();
        this.destroyFriendList$.complete();

        this.updateFriendList$.next();
        this.updateFriendList$.complete();
    }
}
