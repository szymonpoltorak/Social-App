import { Injectable } from '@angular/core';
import { FormControl, Validators } from "@angular/forms";
import { EmailValidation } from "../../../core/enums/EmailValidation";
import { PasswordValidation } from "../../../core/enums/PasswordValidation";

@Injectable({
    providedIn: 'root'
})
export class LoginControlProviderService {
    emailControl: FormControl = new FormControl(EmailValidation.EMAIL_VALUE,
        [
            Validators.required, Validators.pattern(EmailValidation.EMAIL_PATTERN)
        ]
    );

    passwordControl: FormControl = new FormControl(
        PasswordValidation.PASSWORD_VALUE,
        [
            Validators.required,
            Validators.minLength(PasswordValidation.PASSWORD_MIN_LENGTH),
            Validators.maxLength(PasswordValidation.PASSWORD_MAX_LENGTH),
            Validators.pattern(PasswordValidation.PASSWORD_PATTERN),
        ]
    );

    constructor() {
    }
}
