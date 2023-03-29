import { Component, OnInit } from "@angular/core";
import { FormBuilder, FormGroup } from "@angular/forms";
import { RegisterControlProviderService } from "../services/register-control-provider.service";

@Component({
    selector: "app-register",
    templateUrl: "./register.component.html",
    styleUrls: ["./register.component.scss"]
})
export class RegisterComponent implements OnInit{
    registerForm!: FormGroup;

    constructor(private formBuilder: FormBuilder, public controlProvider: RegisterControlProviderService) {
    }

    makeRedirection(): void {
    }

    ngOnInit(): void {
        this.registerForm = this.formBuilder.group({
            nameInputs: this.formBuilder.group({
                firstName: this.controlProvider.nameControl,
                lastName: this.controlProvider.surnameControl
            }),
            emailDateInputs: this.formBuilder.group({
                email: this.controlProvider.emailControl,
                date: this.controlProvider.dateControl
            }),
            passwordInputs: this.formBuilder.group({
                userPassword: this.controlProvider.passwordControl,
                repeatPassword: this.controlProvider.repeatPasswordControl
            })
        });
    }
}
