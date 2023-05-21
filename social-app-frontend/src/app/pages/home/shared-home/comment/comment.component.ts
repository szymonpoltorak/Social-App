import { Component, Input } from '@angular/core';

@Component({
  selector: 'app-comment',
  templateUrl: './comment.component.html',
  styleUrls: ['./comment.component.scss']
})
export class CommentComponent {
    @Input() commentAuthor !: string;
    @Input() commentDate !: Date;
    @Input() commentContent !: string;
    @Input() commentId !: number;
}
