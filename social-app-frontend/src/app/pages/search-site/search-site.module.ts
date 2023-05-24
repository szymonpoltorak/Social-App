import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { SearchSiteComponent } from "@pages/search-site/search-site.component";
import { SharedHomeModule } from "@home/shared-home/shared-home.module";
import { SearchSiteRoutingModule } from "@pages/search-site/search-site-routing.module";
import { MatDividerModule } from "@angular/material/divider";


@NgModule({
    declarations: [
        SearchSiteComponent
    ],
    imports: [
        CommonModule,
        SharedHomeModule,
        SearchSiteRoutingModule,
        MatDividerModule,
    ]
})
export class SearchSiteModule {
}
