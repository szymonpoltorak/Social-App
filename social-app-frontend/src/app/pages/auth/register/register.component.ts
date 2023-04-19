import { Component, OnInit } from "@angular/core";
import { AbstractControl, FormGroup } from "@angular/forms";
import { RegisterControlProviderService } from "../services/register-control-provider.service";
import { FormFieldNames } from "../../../core/enums/FormFieldNames";
import { MatDialog } from "@angular/material/dialog";
import { DialogContents } from "../../../core/enums/DialogContents";
import { DialogService } from "../services/dialog.service";
import { FormBuildingService } from "../services/form-building.service";
import { AuthService } from "../services/auth.service";
import { RegisterRequest } from "../../../core/data/register-request";

@Component({
    selector: "app-register",
    templateUrl: "./register.component.html",
    styleUrls: ["./register.component.scss"]
})
export class RegisterComponent implements OnInit {
    registerForm!: FormGroup;
    wasSubmitClicked: boolean = false;
    passwordMismatch!: boolean;
    private dialogListItems !: Array<string>;
    private paragraphContent !: string;

    constructor(public controlProvider: RegisterControlProviderService,
                private formBuildingService: FormBuildingService,
                private notFilled: MatDialog,
                private dialogService: DialogService,
                private authService: AuthService) {
    }

    makeRedirection(): void {
        if (this.registerForm.invalid) {
            this.wasSubmitClicked = true;

            this.dialogService.openNotFilledDialog(this.notFilled, this.paragraphContent, this.dialogListItems);

            return;
        }
        const request = this.buildRegisterRequest();

        console.log(request);

        this.authService.registerUser(request);
    }

    ngOnInit(): void {
        this.registerForm = this.formBuildingService.buildRegisterForm();

        this.registerForm.get(FormFieldNames.PASSWORD_GROUP)?.valueChanges.subscribe(() => {
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
}
