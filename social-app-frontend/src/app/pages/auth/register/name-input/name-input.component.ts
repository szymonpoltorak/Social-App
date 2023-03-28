import { Component, Input, OnInit } from '@angular/core';
import { FormControl, Validators } from "@angular/forms";
import { NameValidation } from 'src/app/core/enums/NameValidation';

@Component({
    selector: 'app-name-input',
    templateUrl: './name-input.component.html',
    styleUrls: ['./name-input.component.scss']
})
export class NameInputComponent {
    @Input() message: string = "";

    nameControl: FormControl = new FormControl('',
        [Validators.required, Validators.maxLength(NameValidation.NAME_MAX_LENGTH),
            Validators.minLength(NameValidation.NAME_MIN_LENGTH), Validators.pattern(NameValidation.NAME_PATTERN)]);
}
