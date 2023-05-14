import { Component, Input, OnInit } from '@angular/core';
import { PostService } from "@core/services/post.service";

@Component({
    selector: 'app-post',
    templateUrl: './post.component.html',
    styleUrls: ['./post.component.scss']
})
export class PostComponent implements OnInit {
    @Input() postAuthor!: string;
    @Input() postContent!: string;
    @Input() postDate !: Date;
    @Input() numOfLikes!: number;
    @Input() numOfComments!: number;
    @Input() postId !: number;
    @Input() username !: string;
    isPostLiked!: boolean;
    isFriendAdded!: boolean;
    @Input() currentUser !: string;

    constructor(private postService: PostService) {
    }

    updatePostLike(): void {
        this.isPostLiked = !this.isPostLiked;
        this.numOfLikes = this.isPostLiked ? this.numOfLikes + 1 : this.numOfLikes - 1;
    }

    updateFriendStatus(): void {
        this.isFriendAdded = !this.isFriendAdded;
    }

    ngOnInit(): void {
        console.log(`Post Author: ${ this.postAuthor }`);
        console.log(`Current user : ${ this.currentUser }`);
        console.log(`Username : ${this.username}`);
        console.log(`Are they equal ? ${this.username === this.currentUser}`);
    }
}
