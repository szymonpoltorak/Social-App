import { Component, OnDestroy, OnInit } from "@angular/core";
import { AbstractControl, FormGroup } from "@angular/forms";
import { FormFieldNames } from "@core/enums/FormFieldNames";
import { DialogContents } from "@core/enums/DialogContents";
import { RegisterRequest } from "@core/data/register-request";
import { RegisterInterface } from "@core/interfaces/auth/RegisterInterface";
import { RegisterControlProviderService } from "@core/services/auth/register-control-provider.service";
import { AuthDialogService } from "@core/services/auth/auth-dialog.service";
import { AuthService } from "@core/services/auth/auth.service";
import { RoutePaths } from "@core/enums/RoutePaths";
import { AuthResponse } from "@core/data/auth-response";
import { UserService } from "@services/utils/user.service";
import { AuthConstants } from "@core/enums/AuthConstants";
import { UtilService } from "@services/utils/util.service";
import { StorageKeys } from "@core/enums/StorageKeys";
import { Subject, takeUntil } from "rxjs";

@Component({
    selector: "app-register",
    templateUrl: "./register.component.html",
    styleUrls: ["./register.component.scss"]
})
export class RegisterComponent implements OnInit, RegisterInterface, OnDestroy {
    registerForm!: FormGroup;
    wasSubmitClicked: boolean = false;
    passwordMismatch!: boolean;
    private dialogListItems !: Array<string>;
    private paragraphContent !: string;
    private destroyRegister$: Subject<void> = new Subject<void>();

    constructor(public controlProvider: RegisterControlProviderService,
                private dialogService: AuthDialogService,
                private authService: AuthService,
                private userService: UserService,
                private utilService: UtilService) {
    }

    makeRedirection(): void {
        if (this.registerForm.invalid) {
            this.wasSubmitClicked = true;

            this.dialogService.openDialogWindow(this.paragraphContent, this.dialogListItems, DialogContents.FORM_HEADER);

            return;
        }
        const request: RegisterRequest = this.buildRegisterRequest();

        this.authService.registerUser(request)
            .pipe(takeUntil(this.destroyRegister$))
            .subscribe((data: AuthResponse): void => {
            if (data.authToken === AuthConstants.NO_TOKEN) {
                this.dialogService.openDialogWindow(DialogContents.REGISTER_USER_EXISTS_PARAGRAPH,
                    [DialogContents.REGISTER_ITEMS], DialogContents.FORM_HEADER);

                return;
            }
            this.userService.setUserAuthentication = true;

            const username: string = this.registerForm.get(FormFieldNames.EMAIL_DATE_GROUP)
                ?.get(FormFieldNames.EMAIL_FIELD)?.value;

            this.utilService.addValueToStorage(StorageKeys.USERNAME, username);

            this.authService.saveData(data);

            this.utilService.navigate(RoutePaths.HOME_PATH);
        });
    }

    ngOnInit(): void {
        this.registerForm = this.controlProvider.buildRegisterForm();

        this.registerForm.get(FormFieldNames.PASSWORD_GROUP)?.valueChanges.subscribe((): void => {
            this.passwordMismatch = <boolean>this.registerForm.get(FormFieldNames.PASSWORD_GROUP)?.invalid;
        });

        this.dialogListItems = [DialogContents.REGISTER_REQUIRED, DialogContents.REGISTER_YEAR,
            DialogContents.REGISTER_PASSWORD, DialogContents.REGISTER_SAME_PASSWORDS];
        this.paragraphContent = DialogContents.REGISTER_PARAGRAPH;
    }

    private buildRegisterRequest(): RegisterRequest {
        const registerRequest: RegisterRequest = new RegisterRequest();
        const nameGroup: AbstractControl<any, any> = this.registerForm.get(FormFieldNames.NAME_GROUP)!;
        const emailDateGroup: AbstractControl<any, any> = this.registerForm.get(FormFieldNames.EMAIL_DATE_GROUP)!;
        const passwordGroup: AbstractControl<any, any> = this.registerForm.get(FormFieldNames.PASSWORD_GROUP)!;

        registerRequest.name = nameGroup.get(FormFieldNames.NAME_FIELD)!.value;

        registerRequest.surname = nameGroup.get(FormFieldNames.SURNAME_FIELD)!.value;

        registerRequest.email = emailDateGroup.get(FormFieldNames.EMAIL_FIELD)!.value;

        registerRequest.dateOfBirth = emailDateGroup.get(FormFieldNames.DATE_NAME)!.value;

        registerRequest.password = passwordGroup.get(FormFieldNames.PASSWORD_FIELD)!.value;

        return registerRequest;
    }

    ngOnDestroy(): void {
        this.destroyRegister$.next();
        this.destroyRegister$.complete();
    }
}
