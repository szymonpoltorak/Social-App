import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';

import {MatFormFieldModule} from '@angular/material/form-field';
import {MatInputModule} from '@angular/material/input';
import {ReactiveFormsModule} from '@angular/forms'
import {LoginFieldComponent} from "./login-field/login-field.component";
import {AuthComponent} from "./auth.component";
import {AppRoutingModule} from "./app-routing.module";
import {PasswordFieldComponent} from './password-field/password-field.component';
import {MatIconModule} from '@angular/material/icon';
import {MatButtonModule} from '@angular/material/button';


@NgModule({
    declarations: [
        LoginFieldComponent,
        AuthComponent,
        PasswordFieldComponent
    ],
    exports: [
        AuthComponent
    ],
    imports: [
        CommonModule,
        MatFormFieldModule,
        MatInputModule,
        ReactiveFormsModule,
        AppRoutingModule,
        MatIconModule,
        MatButtonModule
    ]
})
export class AuthModule {
}
