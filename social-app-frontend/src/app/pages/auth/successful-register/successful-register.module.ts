import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { SuccessfulRegisterRoutingModule } from './successful-register-routing.module';
import { SuccessfulRegisterComponent } from './successful-register.component';


@NgModule({
    declarations: [
        SuccessfulRegisterComponent
    ],
    imports: [
        CommonModule,
        SuccessfulRegisterRoutingModule
    ]
})
export class SuccessfulRegisterModule {
}
