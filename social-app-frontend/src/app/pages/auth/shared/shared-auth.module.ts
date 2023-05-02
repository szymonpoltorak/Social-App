import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { EmailFieldComponent } from "./email-field/email-field.component";
import { PasswordFieldComponent } from "./password-field/password-field.component";
import { ReactiveFormsModule } from "@angular/forms";
import { LogoComponent } from './logo/logo.component';
import { NotFilledDialogComponent } from './not-filled-dialog/not-filled-dialog.component';
import { MatButtonModule } from "@angular/material/button";
import { MatDialogModule } from "@angular/material/dialog";


@NgModule({
    declarations: [
        EmailFieldComponent,
        PasswordFieldComponent,
        LogoComponent,
        NotFilledDialogComponent,
    ],
    exports: [
        EmailFieldComponent,
        PasswordFieldComponent,
        LogoComponent,
    ],
    imports: [
        CommonModule,
        ReactiveFormsModule,
        MatButtonModule,
        MatDialogModule,
    ]
})
export class SharedAuthModule {
}
