import { Component, Input } from '@angular/core';

@Component({
    selector: 'app-post',
    templateUrl: './post.component.html',
    styleUrls: ['./post.component.scss']
})
export class PostComponent {
    @Input() postAuthor!: string;
    @Input() postLocation!: string;
    @Input() postContent!: string;
    @Input() postDate !: Date;
    @Input() numOfLikes!: number;
    @Input() numOfComments!: number;
    @Input() postId !: number;
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
