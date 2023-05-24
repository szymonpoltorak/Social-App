import { Component, EventEmitter, Input, OnDestroy, Output } from '@angular/core';
import { UtilService } from "@services/utils/util.service";
import { UserHomeDataService } from "@core/services/home/user-home-data.service";
import { HomeApiCalls } from "@core/enums/HomeApiCalls";
import { Subject, takeUntil } from "rxjs";

@Component({
    selector: 'app-friend',
    templateUrl: './friend.component.html',
    styleUrls: ['./friend.component.scss']
})
export class FriendComponent implements OnDestroy {
    private destroyFriend$ : Subject<void> = new Subject<void>();
    private addFriend$: Subject<void> = new Subject<void>();
    @Input() friendUsername !: string;
    @Input() friendName !: string;
    @Input() friendJob !: string;
    @Input() isUsersFriend : boolean = true;
    @Output() friendRemoval: EventEmitter<string> = new EventEmitter<string>();

    constructor(private utilService: UtilService,
                private userDataService: UserHomeDataService) {
    }

    removeUserFromFriends(): void {
        this.userDataService.manageUsersFriendEndpoints(this.friendUsername, HomeApiCalls.REMOVE_FRIEND)
            .pipe(takeUntil(this.destroyFriend$))
            .subscribe((): void => {
                this.isUsersFriend = !this.isUsersFriend;

                this.friendRemoval.emit(this.friendUsername);
            });
    }

    addUserToFriends(): void {
        this.userDataService.manageUsersFriendEndpoints(this.friendUsername, HomeApiCalls.ADD_FRIEND)
            .pipe(takeUntil(this.addFriend$))
            .subscribe((): void => {
                this.isUsersFriend = !this.isUsersFriend;
            });
    }

    ngOnDestroy(): void {
        this.destroyFriend$.next();
        this.destroyFriend$.complete();
    }
}
