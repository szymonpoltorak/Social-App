import { Injectable } from '@angular/core';
import { MatDialog, MatDialogRef } from "@angular/material/dialog";
import { NotFilledDialogComponent } from "@auth/shared/not-filled-dialog/not-filled-dialog.component";
import { FormDialogInterface } from "@interfaces/auth/FormDialogInterface";

@Injectable({
    providedIn: 'root'
})
export class AuthDialogService implements FormDialogInterface {
    constructor(private notFilled: MatDialog) {
    }

    openDialogWindow(paragraphContent: string, dialogListItems: Array<string>, header: string): void {
        this.openNotFilledDialog(this.notFilled, paragraphContent, dialogListItems, header);
    }

    openNotFilledDialog(notFilled: MatDialog,
                        paragraphContent: string,
                        dialogListItems: Array<string>, header: string): void {
        const dialog: MatDialogRef<NotFilledDialogComponent> = notFilled.open(NotFilledDialogComponent, {
            data: {
                paragraphContent: paragraphContent,
                listItems: dialogListItems,
                dialogHeader: header
            }
        });
        dialog.componentInstance.closeEvent.subscribe(() => dialog.close());
    }
}
