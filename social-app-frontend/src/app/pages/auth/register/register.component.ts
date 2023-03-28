import { Component, ViewChild } from "@angular/core";
import { Router } from "@angular/router";
import { EmailFieldComponent } from "../shared/email-field/email-field.component";
import { PasswordFieldComponent } from "../shared/password-field/password-field.component";
import { DateComponent } from "./date/date.component";
import { NameInputComponent } from "./name-input/name-input.component";

@Component({
    selector: "app-register",
    templateUrl: "./register.component.html",
    styleUrls: ["./register.component.scss"],
})
export class RegisterComponent {
    constructor(
        private router: Router,
        @ViewChild("name") nameField: NameInputComponent,
        @ViewChild("surname") surnameField: NameInputComponent,
        @ViewChild("email") emailField: EmailFieldComponent,
        @ViewChild("date") dateField: DateComponent,
        @ViewChild("password") passwordField: PasswordFieldComponent,
        @ViewChild("repeatPassword") repeatField: PasswordFieldComponent
    ) {}

    public validateFields(): void {
        if (this.passwordField.message !== this.repeatField.message) {
            return;
        }
        if (
            this.nameField.nameControl.invalid ||
            this.dateField.dateControl.invalid ||
            this.emailField.emailControl.invalid
        ) {
            alert("KICIA");
            return;
        }
        if (
            this.surnameField.nameControl.invalid ||
            this.repeatField.passwordControl.invalid
        ) {
            return;
        }
    }
}
