import { Component } from '@angular/core';
import { Friend } from "@core/interfaces/home/Friend";

@Component({
    selector: 'app-home-friends',
    templateUrl: './home-friends.component.html',
    styleUrls: ['./home-friends.component.scss']
})
export class HomeFriendsComponent {
    friendList: Friend[] = []
}
