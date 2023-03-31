import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
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
        path: 'successful',
        loadChildren: () => import('../successful-register/successful-register.module').then(module => module.SuccessfulRegisterModule),
    }
];

@NgModule({
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule]
})
export class RegisterRoutingModule {
}
