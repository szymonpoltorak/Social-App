import { Component, Input } from "@angular/core";
import { ControlValueAccessor, FormControl, NG_VALUE_ACCESSOR } from "@angular/forms";

@Component({
    selector: "app-password-field",
    templateUrl: "./password-field.component.html",
    styleUrls: ["./password-field.component.scss"],
    providers: [
        {
            provide: NG_VALUE_ACCESSOR,
            useExisting: PasswordFieldComponent,
            multi: true,
        }
    ]
})
export class PasswordFieldComponent implements ControlValueAccessor {
    @Input() password: string = "";
    @Input() passwordControl!: FormControl;
    @Input() wasSubmitClicked: boolean = false;
    @Input() passwordMismatch: boolean = false;

    private onChange: any = () => {
    };

    private onTouched: any = () => {
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
