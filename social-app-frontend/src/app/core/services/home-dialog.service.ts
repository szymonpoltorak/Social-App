import { Injectable } from '@angular/core';
import { MatDialog, MatDialogRef } from "@angular/material/dialog";
import { EditDialogComponent } from "@home/shared-home/edit-dialog/edit-dialog.component";
import { Observable } from "rxjs";

@Injectable({
    providedIn: 'root'
})
export class HomeDialogService {
    constructor(private editDialog: MatDialog) {
    }

    openMatDialogWindow(title: string, url: string): Observable<string> {
        return this.openNotFilledDialog(this.editDialog, title, url);
    }

    openNotFilledDialog(editDialog: MatDialog, title: string, url: string): any {
        const dialog: MatDialogRef<EditDialogComponent> = editDialog.open(EditDialogComponent, {
            data: {
                title: title,
                url: url
            }
        });
        return dialog.componentInstance.closeEvent;
    }

    closeDialog(): void {
        this.editDialog.closeAll();
    }
}
