import { Injectable } from '@angular/core';
import { AbstractControl, ValidatorFn } from "@angular/forms";

@Injectable({
    providedIn: 'root'
})
export class RegisterValidatorService {
    constructor() {
    }

    public passwordMatchValidator(passwordFieldName: string, repeatFieldName: string): ValidatorFn {
        return (control: AbstractControl): { [key: string]: any } | null => {
            const userPassword = control.get(passwordFieldName);
            const repeatPassword = control.get(repeatFieldName);

            return userPassword && repeatPassword && userPassword.value !== repeatPassword.value ? {'passwordMatch': true} : null;
        };
    }
}
