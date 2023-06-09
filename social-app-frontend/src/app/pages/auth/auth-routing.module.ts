import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { RoutePaths } from "@enums/RoutePaths";

const routes: Routes = [
    {
        path: RoutePaths.CURRENT_PATH,
        redirectTo: RoutePaths.LOGIN_AUTH_PATH,
        pathMatch: 'full'
    },
    {
        path: RoutePaths.LOGIN_AUTH_PATH,
        loadChildren: () => import('./login/login.module').then(module => module.LoginModule),
    },
    {
        path: RoutePaths.REGISTER_AUTH_PATH,
        loadChildren: () => import('./register/register.module').then(module => module.RegisterModule)
    }
];

@NgModule({
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule]
})
export class AuthRoutingModule {
}
