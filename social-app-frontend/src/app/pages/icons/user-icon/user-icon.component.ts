import { Component, Input } from '@angular/core';

@Component({
    selector: 'app-user-icon',
    templateUrl: './user-icon.component.html',
    styleUrls: ['./user-icon.component.scss']
})
export class UserIconComponent {
    @Input() width: number = 55;
    @Input() height: number = 55;
}
