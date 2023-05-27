import { Component, OnDestroy, OnInit } from '@angular/core';
import { HomeService } from "@core/services/home/home.service";
import { Subject, takeUntil } from "rxjs";
import { FriendData } from "@core/interfaces/home/FriendData";
import { UtilService } from "@services/utils/util.service";
import { RoutePaths } from "@enums/RoutePaths";

@Component({
    selector: 'app-home',
    templateUrl: './home.component.html',
    styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit, OnDestroy {
    private destroyFriendList$: Subject<void> = new Subject<void>();
    private updateFriendList$: Subject<void> = new Subject<void>();
    friendList: FriendData[] = [];

    constructor(private homeService: HomeService,
                private utilService: UtilService) {
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
