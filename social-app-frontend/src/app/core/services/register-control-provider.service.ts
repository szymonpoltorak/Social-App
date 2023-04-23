import { Injectable } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from "@angular/forms";
import { RegisterValidatorService } from "./register-validator.service";
import { EmailValidation } from "../enums/EmailValidation";
import { NameValidation } from "../enums/NameValidation";
import { PasswordValidation } from "../enums/PasswordValidation";
import { DateValidation } from "../enums/DateValidation";
import { FormFieldNames } from "../enums/FormFieldNames";

@Injectable({
    providedIn: 'root'
})
export class RegisterControlProviderService {
    emailControl: FormControl = new FormControl(EmailValidation.EMAIL_VALUE,
        [
            Validators.required, Validators.pattern(EmailValidation.EMAIL_PATTERN)
        ]
    );

    nameControl: FormControl = new FormControl(NameValidation.NAME_VALUE,
        [
            Validators.required,
            Validators.maxLength(NameValidation.NAME_MAX_LENGTH),
            Validators.minLength(NameValidation.NAME_MIN_LENGTH),
            Validators.pattern(NameValidation.NAME_PATTERN)
        ]
    );

    surnameControl: FormControl = new FormControl(NameValidation.NAME_VALUE,
        [
            Validators.required,
            Validators.maxLength(NameValidation.NAME_MAX_LENGTH),
            Validators.minLength(NameValidation.NAME_MIN_LENGTH),
            Validators.pattern(NameValidation.NAME_PATTERN)
        ]
    );

    passwordControl: FormControl = new FormControl(
        PasswordValidation.PASSWORD_VALUE,
        [
            Validators.required,
            Validators.pattern(PasswordValidation.PASSWORD_PATTERN),
        ]
    );

    repeatPasswordControl: FormControl = new FormControl(
        PasswordValidation.PASSWORD_VALUE,
        [
            Validators.required,
            Validators.pattern(PasswordValidation.PASSWORD_PATTERN),
        ]
    );
    dateControl: FormControl = new FormControl(
        DateValidation.DATE_VALUE,
        [
            Validators.required,
            this.registerValidator.properAgeValidator()
        ]
    );

    constructor(private registerValidator: RegisterValidatorService,
                private formBuilder: FormBuilder) {
    }

    buildRegisterForm(): FormGroup {
        return this.formBuilder.group({
            nameInputs: this.formBuilder.group({
                firstName: this.nameControl,
                lastName: this.surnameControl
            }),
            emailDateInputs: this.formBuilder.group({
                email: this.emailControl,
                date: this.dateControl
            }),
            passwordInputs: this.formBuilder.group({
                    userPassword: this.passwordControl,
                    repeatPassword: this.repeatPasswordControl
                },
                {
                    validator: this.registerValidator.passwordMatchValidator(FormFieldNames.REGISTER_PASSWORD,
                        FormFieldNames.REGISTER_REPEAT_PASSWORD)
                }
            )
        });
    }
}
