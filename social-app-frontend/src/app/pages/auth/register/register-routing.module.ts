import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { SubmitRegisterGuard } from './guards/submit-register.guard';
import { RegisterComponent } from "./register.component";

const routes: Routes = [
    {
        path: '',
        component: RegisterComponent
    },
    {
        path: 'auth/login',
        loadChildren: () => import('../login/login.module').then(module => module.LoginModule)
    },
    {
        path: 'succesful',
        loadChildren: () => import('../succesful-register/succesful-register.module').then(module => module.SuccesfulRegisterModule),
        canActivate:[SubmitRegisterGuard]
    }
];

@NgModule({
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule]
})
export class RegisterRoutingModule {
}
