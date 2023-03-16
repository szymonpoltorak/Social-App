import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';

import {MatFormFieldModule} from '@angular/material/form-field';
import {MatInputModule} from '@angular/material/input';
import {ReactiveFormsModule} from '@angular/forms'
import {LoginFieldComponent} from "./login-field/login-field.component";
import {AuthRoutingModule} from "./auth-routing.module";
import {MatIconModule} from '@angular/material/icon';
import {MatButtonModule} from '@angular/material/button';
import {PasswordFieldComponent} from "./password-field/password-field.component";
import {LoginComponent} from "./login/login.component";


@NgModule({
    declarations: [
        LoginFieldComponent,
        PasswordFieldComponent,
        LoginComponent
    ],
    exports: [
        LoginFieldComponent,
        LoginComponent,
    ],
    imports: [
        CommonModule,
        MatFormFieldModule,
        MatInputModule,
        ReactiveFormsModule,
        AuthRoutingModule,
        MatIconModule,
        MatButtonModule
    ]
})
export class AuthModule {
}
