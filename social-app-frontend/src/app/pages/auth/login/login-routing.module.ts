import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from "./login.component";
import { RoutePaths } from "@enums/RoutePaths";

const routes: Routes = [
    {
        path: RoutePaths.CURRENT_PATH,
        component: LoginComponent
    },
    {
        path: RoutePaths.REGISTER_PATH,
        loadChildren: () => import('../register/register.module').then(module => module.RegisterModule)
    }
];

@NgModule({
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule]
})
export class LoginRoutingModule {
}
