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
        path: "home",
        redirectTo: "home",
    }
];

@NgModule({
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule]
})
export class OauthRoutingModule {
}
