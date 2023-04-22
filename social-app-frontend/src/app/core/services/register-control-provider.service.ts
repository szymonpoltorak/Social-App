import { Injectable } from '@angular/core';
import { FormControl, Validators } from "@angular/forms";
import { RegisterValidatorService } from "./register-validator.service";
import { EmailValidation } from "../enums/EmailValidation";
import { NameValidation } from "../enums/NameValidation";
import { PasswordValidation } from "../enums/PasswordValidation";
import { DateValidation } from "../enums/DateValidation";

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

    constructor(private registerValidator: RegisterValidatorService) {
    }
}
