import { Component, OnInit } from '@angular/core';
import { FormGroup } from "@angular/forms";
import { DialogContents } from "../../../core/enums/DialogContents";
import { LoginRequest } from "../../../core/data/login-request";
import { FormFieldNames } from "../../../core/enums/FormFieldNames";
import { LoginInterface } from "../../../core/interfaces/LoginInterface";
import { LoginControlProviderService } from "../../../core/services/login-control-provider.service";
import { DialogService } from "../../../core/services/dialog.service";
import { AuthService } from "../../../core/services/auth.service";
import { Router } from "@angular/router";
import { RoutePaths } from "../../../core/enums/RoutePaths";
import { AuthResponse } from "../../../core/data/auth-response";
import { UserService } from "../../../core/services/user.service";

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
                private authService: AuthService,
                private router: Router,
                private userService: UserService) {
    }

    ngOnInit(): void {
        this.loginForm = this.controlProvider.buildLoginForm();
        this.paragraphContent = DialogContents.LOGIN_PARAGRAPH;
        this.dialogListItems = [DialogContents.LOGIN_EMAIL, DialogContents.LOGIN_PASSWORD];

        if (this.userService.wasUserLoggedOut) {
            this.dialogService.openDialogWindow(DialogContents.LOGGED_OUT_PARAGRAPH,
                [DialogContents.LOGGED_OUT_ERROR, DialogContents.LOGGED_OUT_BUTTON],
                DialogContents.LOGGED_OUT_HEADER);

            this.userService.setWasUserLoggedOut = false;
        }
    }

    authenticateUser(): void {
        if (this.loginForm.invalid) {
            this.wasSubmitClicked = true;

            this.dialogService.openDialogWindow(this.paragraphContent, this.dialogListItems, DialogContents.FORM_HEADER);

            return;
        }
        const request: LoginRequest = this.buildLoginRequest();

        console.log(request);

        this.authService.loginUser(request).subscribe((data: AuthResponse): void => {
            if (data.authToken === "") {
                this.dialogService.openDialogWindow(DialogContents.LOGIN_WRONG_PARAGRAPH, this.dialogListItems,
                    DialogContents.FORM_HEADER);

                return;
            }
            this.userService.setUserAuthentication = true;

            this.router.navigateByUrl(RoutePaths.HOME_PATH);
        });
    }

    private buildLoginRequest(): LoginRequest {
        const loginRequest: LoginRequest = new LoginRequest();

        loginRequest.username = this.loginForm.get(FormFieldNames.EMAIL_FIELD)!.value;
        loginRequest.password = this.loginForm.get(FormFieldNames.LOGIN_PASSWORD)!.value;

        return loginRequest;
    }
}
