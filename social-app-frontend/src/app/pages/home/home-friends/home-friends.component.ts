import { Component, OnDestroy, OnInit } from '@angular/core';
import { FriendData } from "@core/interfaces/home/FriendData";
import { Subject, takeUntil } from "rxjs";
import { HomeService } from "@core/services/home.service";
import { UserService } from "@core/services/user.service";
import { UtilService } from "@core/services/util.service";
import { StorageKeys } from "@core/enums/StorageKeys";

@Component({
    selector: 'app-home-friends',
    templateUrl: './home-friends.component.html',
    styleUrls: ['./home-friends.component.scss']
})
export class HomeFriendsComponent implements OnInit, OnDestroy {
    private destroyFriendList$: Subject<FriendData[]> = new Subject<FriendData[]>();
    friendList!: FriendData[];

    constructor(private homeService: HomeService,
                private userService: UserService,
                private utilService: UtilService) {
    }

    ngOnInit(): void {
        const username: string = this.utilService.getValueFromStorage(StorageKeys.USERNAME);

        this.homeService.getFriendList(username.substring(1, username.length - 1))
            .pipe(takeUntil(this.destroyFriendList$))
            .subscribe((data: FriendData[]): void => {
                console.log(data);

                this.friendList = data;
            });
    }

    ngOnDestroy(): void {
        this.destroyFriendList$.complete();
    }
}
