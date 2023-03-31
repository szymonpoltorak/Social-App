import { Component, OnInit } from "@angular/core";
import { FormBuilder, FormGroup } from "@angular/forms";
import { RegisterControlProviderService } from "../services/register-control-provider.service";
import { RegisterValidatorService } from "../services/register-validator.service";
import { FormFieldNames } from "../../../core/enums/FormFieldNames";
import { MatDialog } from "@angular/material/dialog";
import { NotFilledDialogComponent } from "../shared/not-filled-dialog/not-filled-dialog.component";

@Component({
    selector: "app-register",
    templateUrl: "./register.component.html",
    styleUrls: ["./register.component.scss"]
})
export class RegisterComponent implements OnInit {
    registerForm!: FormGroup;
    wasSubmitClicked: boolean = false;
    passwordMismatch!: boolean;

    constructor(private formBuilder: FormBuilder,
                public controlProvider: RegisterControlProviderService,
                private registerValidator: RegisterValidatorService,
                public notFilled: MatDialog) {
    }

    makeRedirection(): void {
        if (this.registerForm.invalid) {
            this.wasSubmitClicked = true;

            const dialog = this.notFilled.open(NotFilledDialogComponent);
            dialog.componentInstance.close.subscribe(() => dialog.close());

            return;
        }
    }

    ngOnInit(): void {
        this.registerForm = this.formBuilder.group({
            nameInputs: this.formBuilder.group({
                firstName: this.controlProvider.nameControl,
                lastName: this.controlProvider.surnameControl
            }),
            emailDateInputs: this.formBuilder.group({
                email: this.controlProvider.emailControl,
                date: this.controlProvider.dateControl
            }),
            passwordInputs: this.formBuilder.group({
                    userPassword: this.controlProvider.passwordControl,
                    repeatPassword: this.controlProvider.repeatPasswordControl
                },
                {
                    validator: this.registerValidator.passwordMatchValidator(FormFieldNames.REGISTER_PASSWORD,
                        FormFieldNames.REGISTER_REPEAT_PASSWORD)
                }
            )
        });

        this.registerForm.get(FormFieldNames.PASSWORD_GROUP)?.valueChanges.subscribe(() => {
            this.passwordMismatch = <boolean>this.registerForm.get(FormFieldNames.PASSWORD_GROUP)?.invalid;
        });
    }
}
