import { Component, Input, OnInit } from '@angular/core';
import { FormControl, Validators } from "@angular/forms";

@Component({
    selector: 'app-name-input',
    templateUrl: './name-input.component.html',
    styleUrls: ['./name-input.component.scss']
})
export class NameInputComponent {
    @Input() message: string = "";

    nameControl: FormControl = new FormControl('',
        [Validators.required, Validators.maxLength(20),
            Validators.minLength(3), Validators.pattern('[a-zA-Z]+')]);
}
