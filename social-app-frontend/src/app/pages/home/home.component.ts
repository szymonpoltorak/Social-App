import { Component, EventEmitter, HostListener, OnDestroy, OnInit } from '@angular/core';
import { HomeService } from "@core/services/home/home.service";
import { Observable, of, Subject, takeUntil } from "rxjs";
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
    event: EventEmitter<void> = new EventEmitter<void>();
    areFriendsVisible: boolean = false;
    areAllVisible: boolean = true;

    constructor(private homeService: HomeService,
                private utilService: UtilService) {
    }

    hideColumn(): void {
        this.areAllVisible = window.innerWidth >= 1250;
        this.areFriendsVisible = !this.areFriendsVisible;

        this.event.emit();
    }

    @HostListener('window:resize', ['$event'])
    onWindowResize(event: any) {
        this.areAllVisible = window.innerWidth >= 1250;
    }

    shouldFriendsBeVisible(): Observable<boolean> {
        if (window.innerWidth >= 1250) {
            return of(true);
        }
        return of(this.areFriendsVisible);
    }

    shouldUserBeVisible(): Observable<boolean> {
        if (window.innerWidth >= 1250) {
            return of(true);
        }
        return of(!this.areFriendsVisible);
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
