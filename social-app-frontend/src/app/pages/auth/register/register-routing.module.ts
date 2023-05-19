import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { RegisterComponent } from "./register.component";
import { RoutePaths } from "@enums/RoutePaths";

const routes: Routes = [
    {
        path: RoutePaths.CURRENT_PATH,
        component: RegisterComponent
    },
    {
        path: RoutePaths.LOGIN_PATH,
        loadChildren: () => import('../login/login.module').then(module => module.LoginModule)
    }
];

@NgModule({
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule]
})
export class RegisterRoutingModule {
}
