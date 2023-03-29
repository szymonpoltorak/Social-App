import { Component, Input } from '@angular/core';
import { ControlValueAccessor, FormControl, NG_VALUE_ACCESSOR } from "@angular/forms";

@Component({
    selector: 'app-name-input',
    templateUrl: './name-input.component.html',
    styleUrls: ['./name-input.component.scss'],
    providers: [
        {
            provide: NG_VALUE_ACCESSOR,
            useExisting: NameInputComponent,
            multi: true,
        }
    ]
})
export class NameInputComponent implements ControlValueAccessor{
    @Input() name: string = "";
    @Input() nameControl!: FormControl;

    private onChange = () => {};

    private onTouched = () => {};

    registerOnChange(onChange: any): void {
        this.onChange = onChange;
    }

    registerOnTouched(onTouched: any): void {
        this.onTouched = onTouched;
    }

    writeValue(name: string): void {
    }
}
