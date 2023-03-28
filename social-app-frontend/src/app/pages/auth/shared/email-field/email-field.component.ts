import { Component } from '@angular/core';
import { FormControl, Validators } from '@angular/forms';
import { EmailValidation } from 'src/app/core/enums/EmailValidation';

@Component({
    selector: 'app-email-field',
    templateUrl: './email-field.component.html',
    styleUrls: ['./email-field.component.scss']
})
export class EmailFieldComponent {
    emailControl: FormControl = new FormControl(EmailValidation.EMAIL_VALUE,
        [Validators.required, Validators.pattern(EmailValidation.EMAIL_PATTERN)]);
}
