import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { EmailFieldComponent } from "./email-field/email-field.component";
import { PasswordFieldComponent } from "./password-field/password-field.component";
import { ReactiveFormsModule } from "@angular/forms";
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
        ReactiveFormsModule,
    ]
})
export class SharedModule {
}
