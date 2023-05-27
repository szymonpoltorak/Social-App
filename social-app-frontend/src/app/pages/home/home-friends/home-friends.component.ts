import { Component, Input } from '@angular/core';
import { FriendData } from "@core/interfaces/home/FriendData";
import { HomeFriendsInterface } from "@interfaces/home/HomeFriendsInterface";

@Component({
    selector: 'app-home-friends',
    templateUrl: './home-friends.component.html',
    styleUrls: ['./home-friends.component.scss']
})
export class HomeFriendsComponent implements HomeFriendsInterface {
    @Input() friendList: FriendData[] = [];

    removeFriendFromList(friendUsername: string): void {
        this.friendList = this.friendList.filter((friend: FriendData): boolean => friend.friendUsername !== friendUsername);
    }
}
