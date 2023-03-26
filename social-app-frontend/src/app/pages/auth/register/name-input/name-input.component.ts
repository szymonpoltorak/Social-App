import { Component, Input } from '@angular/core';

@Component({
    selector: 'app-name-input',
    templateUrl: './name-input.component.html',
    styleUrls: ['./name-input.component.scss']
})
export class NameInputComponent {
    @Input() message = "";
}
