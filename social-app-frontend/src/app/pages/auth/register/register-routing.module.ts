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
    }
];

@NgModule({
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule]
})
export class RegisterRoutingModule {
}
