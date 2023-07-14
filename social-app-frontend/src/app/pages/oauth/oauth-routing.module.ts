import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { RoutePaths } from "@enums/RoutePaths";
import { OauthComponent } from "@pages/oauth/oauth.component";

const routes: Routes = [
    {
        path: RoutePaths.CURRENT_PATH,
        component: OauthComponent
    },
    {
        path: RoutePaths.HOME_PATH,
        redirectTo: RoutePaths.HOME_PATH,
    }
];

@NgModule({
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule]
})
export class OauthRoutingModule {
}
