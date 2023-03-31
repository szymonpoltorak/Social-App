import {Component, EventEmitter, Output} from '@angular/core';

@Component({
  selector: 'app-not-filled-dialog',
  templateUrl: './not-filled-dialog.component.html',
  styleUrls: ['./not-filled-dialog.component.scss']
})
export class NotFilledDialogComponent {
    @Output() close: EventEmitter<any> = new EventEmitter();
}
