import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { SuccesfulRegisterComponent } from './succesful-register.component';

const routes: Routes = [
  {
    path: '',
    component: SuccesfulRegisterComponent
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class SuccesfulRegisterRoutingModule {

}
