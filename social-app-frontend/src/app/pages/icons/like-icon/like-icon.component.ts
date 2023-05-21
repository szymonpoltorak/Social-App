import { Component, Input } from '@angular/core';

@Component({
    selector: 'app-like-icon',
    templateUrl: './like-icon.component.html',
    styleUrls: ['./like-icon.component.scss']
})
export class LikeIconComponent {
    @Input() isLiked!: boolean;
}
