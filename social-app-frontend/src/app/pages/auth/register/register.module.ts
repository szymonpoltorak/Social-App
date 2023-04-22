import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { RegisterRoutingModule } from './register-routing.module';
import { RegisterComponent } from "./register.component";
import { SharedModule } from "../shared/shared.module";
import { NameInputComponent } from './name-input/name-input.component';
import { FormsModule, ReactiveFormsModule } from "@angular/forms";
import { DateComponent } from './date/date.component';
import { DialogService } from "../../../core/services/dialog.service";
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
        SharedModule,
        ReactiveFormsModule,
        FormsModule,
        MatDialogModule
    ],
    providers: [
        DialogService
    ]
})
export class RegisterModule {
}
