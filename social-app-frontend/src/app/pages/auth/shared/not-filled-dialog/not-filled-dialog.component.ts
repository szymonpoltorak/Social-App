import { Component, EventEmitter, Inject, Output } from '@angular/core';
import { MAT_DIALOG_DATA } from "@angular/material/dialog";

@Component({
    selector: 'app-not-filled-dialog',
    templateUrl: './not-filled-dialog.component.html',
    styleUrls: ['./not-filled-dialog.component.scss']
})
export class NotFilledDialogComponent {
    @Output() closeEvent: EventEmitter<any> = new EventEmitter();
    paragraphContent: string;
    listItems: Array<String>;

    constructor(@Inject(MAT_DIALOG_DATA) data: any) {
        this.paragraphContent = data.paragraphContent;
        this.listItems = data.listItems;
    }
}
