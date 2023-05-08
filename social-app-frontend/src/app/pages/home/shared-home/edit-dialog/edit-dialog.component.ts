import { Component, EventEmitter, Inject, Output } from '@angular/core';
import { MAT_DIALOG_DATA } from "@angular/material/dialog";

@Component({
  selector: 'app-edit-dialog',
  templateUrl: './edit-dialog.component.html',
  styleUrls: ['./edit-dialog.component.scss']
})
export class EditDialogComponent {
    @Output() closeEvent: EventEmitter<any> = new EventEmitter();
    title: string;
    url: string;
    linkValue !: string;

    constructor(@Inject(MAT_DIALOG_DATA) data: any) {
                // private userDataService: UserHomeDataService) {
        this.title = data.title;
        this.url = data.url;
    }

    exit(): void {
        this.closeEvent.emit();
    }

    submitValue(): void {
        if (!this.linkValue.match("^(?=.{16,})(?:(?:https?|ftp):\\/\\/)?(?:www\\.)?[a-zA-Z0-9\\-\\.]+\\.[a-zA-Z]{2,}(?:\\/[\\w#]*)*\\/?$")) {
            return;
        }
    }
}
