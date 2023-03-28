import { Component, Input } from '@angular/core';
import { FormControl, Validators } from '@angular/forms';
import { PasswordValidation } from 'src/app/core/enums/PasswordValidation';

@Component({
    selector: 'app-password-field',
    templateUrl: './password-field.component.html',
    styleUrls: ['./password-field.component.scss']
})
export class PasswordFieldComponent {
    @Input()
    message: string = "";
    passwordControl: FormControl = new FormControl('',
        [Validators.required, Validators.minLength(PasswordValidation.PASSWORD_MIN_LENGTH),
            Validators.maxLength(PasswordValidation.PASSWORD_MAX_LENGTH)]);
}
