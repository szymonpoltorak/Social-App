import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from "@angular/forms";
import { LoginControlProviderService } from "../services/login-control-provider.service";
import { NotFilledDialogComponent } from "../shared/not-filled-dialog/not-filled-dialog.component";
import { MatDialog } from "@angular/material/dialog";
import { DialogContents } from "../../../core/enums/DialogContents";

@Component({
    selector: 'app-login',
    templateUrl: './login.component.html',
    styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {
    private dialogListItems !: Array<string>;
    private paragraphContent !: string;
    loginForm !: FormGroup;
    wasSubmitClicked: boolean = false;

    constructor(private formBuilder: FormBuilder,
                public controlProvider: LoginControlProviderService,
                public notFilled: MatDialog) {
    }

    ngOnInit(): void {
        this.loginForm = this.formBuilder.group({
            email: this.controlProvider.emailControl,
            password: this.controlProvider.passwordControl
        });

        this.paragraphContent = DialogContents.LOGIN_PARAGRAPH;
        this.dialogListItems = [DialogContents.LOGIN_EMAIL, DialogContents.LOGIN_PASSWORD];
    }

    authenticateUser(): void {
        if (this.loginForm.invalid) {
            this.wasSubmitClicked = true;

            const dialog = this.notFilled.open(NotFilledDialogComponent,{
                data: {
                    paragraphContent : this.paragraphContent,
                    listItems : this.dialogListItems
                }
            });
            dialog.componentInstance.closeEvent.subscribe(() => dialog.close());

            return;
        }
    }
}
