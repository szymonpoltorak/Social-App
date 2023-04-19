import { Injectable } from '@angular/core';
import { FormBuilder, FormGroup } from "@angular/forms";
import { FormFieldNames } from "../../../core/enums/FormFieldNames";
import { RegisterControlProviderService } from "./register-control-provider.service";
import { RegisterValidatorService } from "./register-validator.service";
import { LoginControlProviderService } from "./login-control-provider.service";
import { FormBuilderInterface } from "../../../core/interfaces/FormBuilderInterface";

@Injectable({
    providedIn: 'root'
})
export class FormBuildingService implements FormBuilderInterface{
    constructor(private formBuilder: FormBuilder,
                private registerControlProvider: RegisterControlProviderService,
                private loginControlProvider: LoginControlProviderService,
                private registerValidator: RegisterValidatorService) {
    }

    buildRegisterForm(): FormGroup {
        return this.formBuilder.group({
            nameInputs: this.formBuilder.group({
                firstName: this.registerControlProvider.nameControl,
                lastName: this.registerControlProvider.surnameControl
            }),
            emailDateInputs: this.formBuilder.group({
                email: this.registerControlProvider.emailControl,
                date: this.registerControlProvider.dateControl
            }),
            passwordInputs: this.formBuilder.group({
                    userPassword: this.registerControlProvider.passwordControl,
                    repeatPassword: this.registerControlProvider.repeatPasswordControl
                },
                {
                    validator: this.registerValidator.passwordMatchValidator(FormFieldNames.REGISTER_PASSWORD,
                        FormFieldNames.REGISTER_REPEAT_PASSWORD)
                }
            )
        });
    }

    buildLoginForm(): FormGroup {
        return this.formBuilder.group({
            email: this.loginControlProvider.emailControl,
            password: this.loginControlProvider.passwordControl
        });
    }
}
