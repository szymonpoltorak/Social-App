import { Injectable } from '@angular/core';
import { MatDialog, MatDialogRef } from "@angular/material/dialog";
import { EditDialogComponent } from "@home/shared-home/edit-dialog/edit-dialog.component";

@Injectable({
    providedIn: 'root'
})
export class HomeDialogService {
    constructor(private editDialog: MatDialog) {
    }

    openMatDialogWindow(title: string, url: string): void {
        this.openNotFilledDialog(this.editDialog, title, url);
    }

    openNotFilledDialog(editDialog: MatDialog, title: string, url: string): void {
        const dialog: MatDialogRef<EditDialogComponent> = editDialog.open(EditDialogComponent, {
            data: {
                title: title,
                url: url
            }
        });
        dialog.componentInstance.closeEvent.subscribe(() => dialog.close());
    }
}
