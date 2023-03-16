import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

const routes: Routes = [
    {
        path: "auth",
        loadChildren: () => import("./pages/auth/auth.module")
            .then(module => module.AuthModule)
    },
    {
        path: '',
        redirectTo: 'auth/login',
        pathMatch: 'full'
    },
    {
        path: 'error',
        loadChildren: () => import("./pages/not-found/not-found.module")
            .then(module => module.NotFoundModule)
    },
    {
        path: '**',
        redirectTo: 'error'
    },
];

@NgModule({
    imports: [RouterModule.forRoot(routes)],
    exports: [RouterModule]
})
export class AppRoutingModule {
}
