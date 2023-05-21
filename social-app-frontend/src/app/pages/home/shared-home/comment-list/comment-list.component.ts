import { Component } from '@angular/core';
import { CommentData } from "@interfaces/home/CommentData";

@Component({
  selector: 'app-comment-list',
  templateUrl: './comment-list.component.html',
  styleUrls: ['./comment-list.component.scss']
})
export class CommentListComponent {
    comments !: CommentData[];
    protected readonly Date = Date;
}
