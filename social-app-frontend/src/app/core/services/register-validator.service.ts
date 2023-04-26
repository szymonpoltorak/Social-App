import { Injectable } from '@angular/core';
import { AbstractControl, ValidatorFn } from "@angular/forms";
import { RegisterValidationInterface } from "../interfaces/auth/RegisterValidationInterface";

@Injectable({
    providedIn: 'root'
})
export class RegisterValidatorService implements RegisterValidationInterface {
    constructor() {
    }

    passwordMatchValidator(passwordFieldName: string, repeatFieldName: string): ValidatorFn {
        return (control: AbstractControl): { [key: string]: any } | null => {
            const userPassword: AbstractControl<any, any> | null = control.get(passwordFieldName);
            const repeatPassword: AbstractControl<any, any> | null = control.get(repeatFieldName);

            return userPassword && repeatPassword && userPassword.value !== repeatPassword.value ? {'passwordMatch': true} : null;
        };
    }

    properAgeValidator(): ValidatorFn {
        return (control: AbstractControl): { [key: string]: any } | null => {
            const currentDateYear: number = new Date().getFullYear();
            const dateFieldValueYear: number = new Date(control.value).getFullYear();
            const userAge: number = currentDateYear - dateFieldValueYear;

            return userAge > 200 || userAge < 13 || dateFieldValueYear > currentDateYear ? {'wrongAge': true} : null;
        };
    }
}
