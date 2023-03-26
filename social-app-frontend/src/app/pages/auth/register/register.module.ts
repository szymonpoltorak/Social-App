import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { RegisterRoutingModule } from './register-routing.module';
import { RegisterComponent } from "./register.component";
import { SharedModule } from "../shared/shared.module";
import { NameInputComponent } from './name-input/name-input.component';


@NgModule({
    declarations: [
        RegisterComponent,
        NameInputComponent,
    ],
    imports: [
        CommonModule,
        RegisterRoutingModule,
        SharedModule,
    ]
})
export class RegisterModule {
}
