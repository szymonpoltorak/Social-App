import { Component, OnDestroy, OnInit } from '@angular/core';
import { FormGroup } from "@angular/forms";
import { DialogContents } from "@core/enums/DialogContents";
import { LoginRequest } from "@core/data/login-request";
import { FormFieldNames } from "@core/enums/FormFieldNames";
import { LoginInterface } from "@core/interfaces/auth/LoginInterface";
import { LoginControlProviderService } from "@core/services/login-control-provider.service";
import { AuthDialogService } from "@core/services/auth-dialog.service";
import { AuthService } from "@core/services/auth.service";
import { RoutePaths } from "@core/enums/RoutePaths";
import { AuthResponse } from "@core/data/auth-response";
import { UserService } from "@core/services/user.service";
import { AuthConstants } from "@core/enums/AuthConstants";
import { StorageKeys } from "@core/enums/StorageKeys";
import { UtilService } from "@core/services/util.service";
import { catchError, Subject, takeUntil, throwError } from "rxjs";

@Component({
    selector: 'app-login',
    templateUrl: './login.component.html',
    styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit, LoginInterface, OnDestroy {
    loginForm !: FormGroup;
    wasSubmitClicked: boolean = false;
    private dialogListItems !: Array<string>;
    private paragraphContent !: string;
    private testDestroy$: Subject<any> = new Subject<any>();
    private loginDestroy$: Subject<any> = new Subject<any>();

    constructor(public controlProvider: LoginControlProviderService,
                private dialogService: AuthDialogService,
                private authService: AuthService,
                private utilService: UtilService,
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

        this.utilService.buildTestData()
            .pipe(takeUntil(this.testDestroy$), catchError(error => {
                this.utilService.clearStorage();

                return throwError(error);
            }))
            .subscribe();
    }

    authenticateUser(): void {
        if (this.loginForm.invalid) {
            this.wasSubmitClicked = true;

            this.dialogService.openDialogWindow(this.paragraphContent, this.dialogListItems, DialogContents.FORM_HEADER);

            return;
        }
        const request: LoginRequest = this.buildLoginRequest();

        console.log(request);

        this.authService.loginUser(request)
            .pipe(takeUntil(this.loginDestroy$))
            .subscribe((data: AuthResponse): void => {
                if (data.authToken === AuthConstants.NO_TOKEN) {
                    this.dialogService.openDialogWindow(DialogContents.LOGIN_WRONG_PARAGRAPH, this.dialogListItems,
                        DialogContents.FORM_HEADER);

                    return;
                }
                const username: string = this.loginForm.get(FormFieldNames.EMAIL_FIELD)?.value;

                this.userService.setUserAuthentication = true;

                this.authService.saveData(data);

                this.utilService.addValueToStorage(StorageKeys.USERNAME, username);
                this.utilService.navigate(RoutePaths.HOME_PATH);
            });
    }

    private buildLoginRequest(): LoginRequest {
        const loginRequest: LoginRequest = new LoginRequest();

        loginRequest.username = this.loginForm.get(FormFieldNames.EMAIL_FIELD)!.value;
        loginRequest.password = this.loginForm.get(FormFieldNames.LOGIN_PASSWORD)!.value;

        return loginRequest;
    }

    ngOnDestroy(): void {
        this.testDestroy$.complete();
        this.loginDestroy$.complete();
    }
}
