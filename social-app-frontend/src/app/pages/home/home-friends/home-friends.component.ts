import { Component } from '@angular/core';
import { FriendInterface } from "../../../core/interfaces/home/FriendInterface";

@Component({
    selector: 'app-home-friends',
    templateUrl: './home-friends.component.html',
    styleUrls: ['./home-friends.component.scss']
})
export class HomeFriendsComponent {
    friendList: FriendInterface[] = [
        {
            friendName: "Jan Kowalski",
            friendJob: "Software Engineer"
        },
        {
            friendName: "Stefan Czarnecki",
            friendJob: "Lecturer"
        },
        {
            friendName: "Jan Sobieski",
            friendJob: "Department of Defense"
        },
        {
            friendName: "Michał Mickiewicz",
            friendJob: "Poet"
        }
    ]
}
