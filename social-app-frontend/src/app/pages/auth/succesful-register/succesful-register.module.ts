import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { SuccesfulRegisterRoutingModule } from './succesful-register-routing.module';
import { SuccesfulRegisterComponent } from './succesful-register.component';


@NgModule({
  declarations: [
    SuccesfulRegisterComponent
  ],
  imports: [
    CommonModule,
    SuccesfulRegisterRoutingModule
  ]
})
export class SuccesfulRegisterModule { }
