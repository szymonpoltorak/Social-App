import { Component, Input } from '@angular/core';
import { ControlValueAccessor, FormControl, NG_VALUE_ACCESSOR } from "@angular/forms";

@Component({
    selector: 'app-date',
    templateUrl: './date.component.html',
    styleUrls: ['./date.component.scss'],
    providers: [
        {
            provide: NG_VALUE_ACCESSOR,
            useExisting: DateComponent,
            multi: true,
        }
    ]
})
export class DateComponent implements ControlValueAccessor {
    @Input() dateControl!: FormControl;
    @Input() wasSubmitClicked: boolean = false;

    registerOnChange(onChange: any): void {
        this.onChange = onChange;
    }

    registerOnTouched(onTouched: any): void {
        this.onTouched = onTouched;
    }

    writeValue(obj: any): void {
    }

    private onChange = () => {
    };

    private onTouched = () => {
    };
}
