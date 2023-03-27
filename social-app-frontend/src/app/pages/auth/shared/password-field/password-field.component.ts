import { Component, Input } from '@angular/core';
import { FormControl, Validators } from '@angular/forms';

@Component({
    selector: 'app-password-field',
    templateUrl: './password-field.component.html',
    styleUrls: ['./password-field.component.scss']
})
export class PasswordFieldComponent {
    @Input()
    message: string = "";
    passwordControl = new FormControl('',
                        [Validators.required, Validators.minLength(8),
                                      Validators.maxLength(20)]);
}
