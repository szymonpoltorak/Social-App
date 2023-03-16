import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { LoginComponent } from "./login.component";
import { LoginFieldComponent } from "./login-field/login-field.component";
import { PasswordFieldComponent } from "./password-field/password-field.component";
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { ReactiveFormsModule } from '@angular/forms';
import { MatIconModule } from '@angular/material/icon';
import { MatButtonModule } from '@angular/material/button';
import { LoginRoutingModule } from "./login-routing.module";


@NgModule({
    declarations: [
        LoginComponent,
        LoginFieldComponent,
        PasswordFieldComponent
    ],
    imports: [
        CommonModule,
        MatFormFieldModule,
        MatInputModule,
        ReactiveFormsModule,
        MatIconModule,
        MatButtonModule,
        LoginRoutingModule
    ]
})
export class LoginModule {
}
