import { Component } from '@angular/core';
import { FormControl, Validators } from '@angular/forms';

@Component({
    selector: 'app-login-field',
    templateUrl: './login-field.component.html',
    styleUrls: ['./login-field.component.scss']
})
export class LoginFieldComponent {
    email = new FormControl('', [Validators.required, Validators.email]);

    getErrorMessage() {
        const emailError: boolean = this.email.hasError('required');

        if (emailError) {
            return 'You must enter a value';
        }
        return emailError ? 'Not a valid email' : '';
    }
}
