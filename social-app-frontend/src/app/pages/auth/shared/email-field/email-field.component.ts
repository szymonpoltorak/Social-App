import { Component } from '@angular/core';
import { FormControl, Validators } from '@angular/forms';

@Component({
    selector: 'app-email-field',
    templateUrl: './email-field.component.html',
    styleUrls: ['./email-field.component.scss']
})
export class EmailFieldComponent {
    email = new FormControl('', [Validators.required, Validators.email]);

    getErrorMessage() {
        const emailError: boolean = this.email.hasError('required');

        if (emailError) {
            return 'You must enter a value';
        }
        return emailError ? 'Not a valid email' : '';
    }
}
