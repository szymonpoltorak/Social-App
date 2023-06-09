import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from "./home.component";
import { RoutePaths } from "@enums/RoutePaths";
import { AuthGuard } from "@core/guards/auth.guard";

const routes: Routes = [
    {
        path: RoutePaths.CURRENT_PATH,
        component: HomeComponent,
        pathMatch: "full"
    },
    {
        path: RoutePaths.SEARCH_SITE,
        loadChildren: () => import("../search-site/search-site.module")
            .then(module => module.SearchSiteModule),
        canActivate: [AuthGuard]
    }
];

@NgModule({
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule]
})
export class HomeRoutingModule {
}
