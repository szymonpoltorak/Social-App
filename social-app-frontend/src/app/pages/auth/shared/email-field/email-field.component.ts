import { Component } from '@angular/core';
import { FormControl, Validators } from '@angular/forms';

@Component({
    selector: 'app-email-field',
    templateUrl: './email-field.component.html',
    styleUrls: ['./email-field.component.scss']
})
export class EmailFieldComponent {
    emailControl: FormControl = new FormControl('',
        [Validators.required, Validators.email]);
}
