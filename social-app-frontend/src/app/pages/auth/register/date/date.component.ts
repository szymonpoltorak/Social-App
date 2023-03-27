import { Component } from '@angular/core';
import { FormControl, Validators } from "@angular/forms";

@Component({
  selector: 'app-date',
  templateUrl: './date.component.html',
  styleUrls: ['./date.component.scss']
})
export class DateComponent {
    dateControl: FormControl = new FormControl('', Validators.required);
}
