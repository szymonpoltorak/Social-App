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
}
