import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { LoginComponent } from "./login.component";
import { LoginRoutingModule } from "./login-routing.module";
import { SharedAuthModule } from "../shared/shared-auth.module";
import { ReactiveFormsModule } from "@angular/forms";
import { AuthDialogService } from "@core/services/auth-dialog.service";
import { MatDialogModule } from "@angular/material/dialog";


@NgModule({
    declarations: [
        LoginComponent
    ],
    imports: [
        CommonModule,
        LoginRoutingModule,
        SharedAuthModule,
        MatDialogModule,
        ReactiveFormsModule
    ],
    providers: [
        AuthDialogService
    ]
})
export class LoginModule {
}
