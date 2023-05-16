import { Component, OnDestroy, OnInit } from '@angular/core';
import { FriendData } from "@core/interfaces/home/FriendData";
import { Subject, takeUntil } from "rxjs";
import { HomeService } from "@core/services/home.service";

@Component({
    selector: 'app-home-friends',
    templateUrl: './home-friends.component.html',
    styleUrls: ['./home-friends.component.scss']
})
export class HomeFriendsComponent implements OnInit, OnDestroy {
    private destroyFriendList$: Subject<void> = new Subject<void>();
    friendList!: FriendData[];

    constructor(private homeService: HomeService) {
    }

    ngOnInit(): void {
        this.homeService.getFriendList()
            .pipe(takeUntil(this.destroyFriendList$))
            .subscribe((data: FriendData[]): void => {
                console.log(data);

                this.friendList = data;
            });
    }

    removeFriendFromList(friendUsername: string): void {
        this.friendList = this.friendList.filter((friend: FriendData): boolean => friend.friendUsername !== friendUsername);
    }

    ngOnDestroy(): void {
        this.destroyFriendList$.next();
        this.destroyFriendList$.complete();
    }
}
