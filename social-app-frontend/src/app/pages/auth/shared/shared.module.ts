import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { EmailFieldComponent } from "./email-field/email-field.component";
import { PasswordFieldComponent } from "./password-field/password-field.component";
import { MatFormFieldModule } from "@angular/material/form-field";
import { MatInputModule } from "@angular/material/input";
import { ReactiveFormsModule } from "@angular/forms";
import { MatIconModule } from "@angular/material/icon";
import { MatButtonModule } from "@angular/material/button";
import { LogoComponent } from './logo/logo.component';


@NgModule({
    declarations: [
        EmailFieldComponent,
        PasswordFieldComponent,
        LogoComponent,
    ],
    exports: [
        EmailFieldComponent,
        PasswordFieldComponent,
        LogoComponent,
    ],
    imports: [
        CommonModule,
        MatFormFieldModule,
        MatInputModule,
        ReactiveFormsModule,
        MatIconModule,
        MatButtonModule
    ]
})
export class SharedModule {
}
