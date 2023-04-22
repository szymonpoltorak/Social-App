import { Component, OnInit } from '@angular/core';
import { FormGroup } from "@angular/forms";
import { MatDialog } from "@angular/material/dialog";
import { DialogContents } from "../../../core/enums/DialogContents";
import { LoginRequest } from "../../../core/data/login-request";
import { FormFieldNames } from "../../../core/enums/FormFieldNames";
import { LoginInterface } from "../../../core/interfaces/LoginInterface";
import { LoginControlProviderService } from "../../../core/services/login-control-provider.service";
import { DialogService } from "../../../core/services/dialog.service";
import { FormBuildingService } from "../../../core/services/form-building.service";
import { AuthService } from "../../../core/services/auth.service";

@Component({
    selector: 'app-login',
    templateUrl: './login.component.html',
    styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit, LoginInterface {
    loginForm !: FormGroup;
    wasSubmitClicked: boolean = false;
    private dialogListItems !: Array<string>;
    private paragraphContent !: string;

    constructor(public controlProvider: LoginControlProviderService,
                private dialogService: DialogService,
                private formBuildingService: FormBuildingService,
                private authService: AuthService) {
    }

    ngOnInit(): void {
        this.loginForm = this.formBuildingService.buildLoginForm();
        this.paragraphContent = DialogContents.LOGIN_PARAGRAPH;
        this.dialogListItems = [DialogContents.LOGIN_EMAIL, DialogContents.LOGIN_PASSWORD];
    }

    authenticateUser(): void {
        if (this.loginForm.invalid) {
            this.wasSubmitClicked = true;

            this.dialogService.openInvalidFormDialog(this.paragraphContent, this.dialogListItems);

            return;
        }
        const request: LoginRequest = this.buildLoginRequest();

        console.log(request);

        this.authService.loginUser(request);
    }

    private buildLoginRequest(): LoginRequest {
        const loginRequest: LoginRequest = new LoginRequest();

        loginRequest.username = this.loginForm.get(FormFieldNames.EMAIL_FIELD)!.value;
        loginRequest.password = this.loginForm.get(FormFieldNames.LOGIN_PASSWORD)!.value;

        return loginRequest;
    }
}
