import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';

const routes: Routes = [
    {
        path: "auth",
        loadChildren: () => import("./pages/auth/auth.module")
            .then((module) => module.AuthModule)
    },
    {
        path: '',
        redirectTo: 'auth/login',
        pathMatch: 'full'
    }
];

@NgModule({
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule]
})
export class AppRoutingModule {
}
