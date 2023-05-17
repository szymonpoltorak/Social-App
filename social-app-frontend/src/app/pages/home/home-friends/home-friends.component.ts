import { Component, Input } from '@angular/core';
import { FriendData } from "@core/interfaces/home/FriendData";

@Component({
    selector: 'app-home-friends',
    templateUrl: './home-friends.component.html',
    styleUrls: ['./home-friends.component.scss']
})
export class HomeFriendsComponent {
    @Input() friendList!: FriendData[];

    constructor() {
    }

    removeFriendFromList(friendUsername: string): void {
        this.friendList = this.friendList.filter((friend: FriendData): boolean => friend.friendUsername !== friendUsername);
    }
}
