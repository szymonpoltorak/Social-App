import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { RegisterRoutingModule } from './register-routing.module';
import { RegisterComponent } from "./register.component";
import { SharedAuthModule } from "../shared/shared-auth.module";
import { NameInputComponent } from './name-input/name-input.component';
import { FormsModule, ReactiveFormsModule } from "@angular/forms";
import { DateComponent } from './date/date.component';
import { AuthDialogService } from "@core/services/auth-dialog.service";
import { MatDialogModule } from "@angular/material/dialog";


@NgModule({
    declarations: [
        RegisterComponent,
        NameInputComponent,
        DateComponent,
    ],
    imports: [
        CommonModule,
        RegisterRoutingModule,
        SharedAuthModule,
        ReactiveFormsModule,
        FormsModule,
        MatDialogModule
    ],
    providers: [
        AuthDialogService
    ]
})
export class RegisterModule {
}
