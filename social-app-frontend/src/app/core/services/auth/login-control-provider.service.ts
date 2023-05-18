import { Injectable } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from "@angular/forms";
import { EmailValidation } from "../../enums/EmailValidation";
import { PasswordValidation } from "../../enums/PasswordValidation";

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
            Validators.pattern(PasswordValidation.PASSWORD_PATTERN),
        ]
    );

    constructor(private formBuilder: FormBuilder) {
    }

    buildLoginForm(): FormGroup {
        return this.formBuilder.group({
            email: this.emailControl,
            password: this.passwordControl
        });
    }
}
