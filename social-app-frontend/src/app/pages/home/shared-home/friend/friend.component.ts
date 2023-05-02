import { Component, Input } from '@angular/core';

@Component({
  selector: 'app-friend',
  templateUrl: './friend.component.html',
  styleUrls: ['./friend.component.scss']
})
export class FriendComponent {
    @Input() friendName!: string;
    @Input() friendJob!: string;
}
