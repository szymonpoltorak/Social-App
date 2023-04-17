import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { SuccessfulRegisterComponent } from './successful-register.component';

const routes: Routes = [
    {
        path: '',
        component: SuccessfulRegisterComponent
    }
];

@NgModule({
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule]
})
export class SuccessfulRegisterRoutingModule {

}
