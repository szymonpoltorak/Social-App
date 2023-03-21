import { Component, Input } from '@angular/core';
import { FormControl, Validators } from '@angular/forms';

@Component({
    selector: 'app-password-field',
    templateUrl: './password-field.component.html',
    styleUrls: ['./password-field.component.scss']
})
export class PasswordFieldComponent {
    @Input()
    message!: string;
    isPasswordHidden: boolean = true;
    password = new FormControl('', [Validators.required]);

    getErrorMessage() {
        return 'You must enter a value';
    }
}
