import { Component, Input } from '@angular/core';
import { ControlValueAccessor, FormControl, NG_VALUE_ACCESSOR } from '@angular/forms';

@Component({
    selector: 'app-email-field',
    templateUrl: './email-field.component.html',
    styleUrls: ['./email-field.component.scss'],
    providers: [
        {
            provide: NG_VALUE_ACCESSOR,
            useExisting: EmailFieldComponent,
            multi: true,
        }
    ]
})
export class EmailFieldComponent implements ControlValueAccessor {
    @Input() emailControl!: FormControl;
    @Input() wasSubmitClicked: boolean = false;

    private onChange = () => {
    };

    private onTouched = () => {
    };

    registerOnChange(onChange: any): void {
        this.onChange = onChange;
    }

    registerOnTouched(onTouched: any): void {
        this.onTouched = onTouched;
    }

    writeValue(obj: any): void {
    }
}
