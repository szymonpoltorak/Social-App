import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { SearchSiteComponent } from "@pages/search-site/search-site.component";
import { RoutePaths } from "@enums/RoutePaths";
import { AuthGuard } from "@core/guards/auth.guard";

const routes: Routes = [
    {
        path: RoutePaths.CURRENT_PATH,
        component: SearchSiteComponent,
        pathMatch: "full"
    },
    {
        path: RoutePaths.HOME_PATH,
        loadChildren: () => import("../home/home.module")
            .then(module => module.HomeModule),
        canActivate: [AuthGuard]
    }
];

@NgModule({
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule]
})
export class SearchSiteRoutingModule {
}
