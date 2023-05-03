import { Component, Input } from '@angular/core';

@Component({
    selector: 'app-post',
    templateUrl: './post.component.html',
    styleUrls: ['./post.component.scss']
})
export class PostComponent {
    @Input() postAuthor!: string;
    @Input() postAuthorLocation!: string;
    @Input() postContent!: string;
    @Input() numOfLikes!: number;
    @Input() numOfComments!: number;
    isPostLiked!: boolean;
    isFriendAdded!: boolean;

    updatePostLike(): void {
        this.isPostLiked = !this.isPostLiked;
        this.numOfLikes = this.isPostLiked ? this.numOfLikes + 1 : this.numOfLikes - 1;
    }

    updateFriendStatus(): void {
        this.isFriendAdded = !this.isFriendAdded;
    }
}
