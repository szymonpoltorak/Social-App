import { Injectable } from '@angular/core';
import { MatDialog, MatDialogRef } from "@angular/material/dialog";
import { NotFilledDialogComponent } from "../../pages/auth/shared/not-filled-dialog/not-filled-dialog.component";

@Injectable({
    providedIn: 'root'
})
export class DialogService {
    openNotFilledDialog(notFilled: MatDialog, paragraphContent: string, dialogListItems: Array<string>): void {
        const dialog: MatDialogRef<NotFilledDialogComponent> = notFilled.open(NotFilledDialogComponent, {
            data: {
                paragraphContent: paragraphContent,
                listItems: dialogListItems
            }
        });
        dialog.componentInstance.closeEvent.subscribe(() => dialog.close());
    }
}
