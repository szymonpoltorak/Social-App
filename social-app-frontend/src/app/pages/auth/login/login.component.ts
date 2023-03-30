import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from "@angular/forms";
import { LoginControlProviderService } from "../services/login-control-provider.service";

@Component({
    selector: 'app-login',
    templateUrl: './login.component.html',
    styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {
    loginForm !: FormGroup;
    wasSubmitClicked: boolean = false;

    constructor(private formBuilder: FormBuilder, public controlProvider: LoginControlProviderService) {
    }

    ngOnInit(): void {
        this.loginForm = this.formBuilder.group({
            email: this.controlProvider.emailControl,
            password: this.controlProvider.passwordControl
        });
    }

    authenticateUser(): void {
        if (this.loginForm.invalid) {
            this.wasSubmitClicked = true;

            alert("Please fill out every form field");

            return;
        }
    }
}
