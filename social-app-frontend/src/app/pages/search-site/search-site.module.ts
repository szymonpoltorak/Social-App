import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { SearchSiteComponent } from "@pages/search-site/search-site.component";
import { SharedHomeModule } from "@home/shared-home/shared-home.module";


@NgModule({
    declarations: [
        SearchSiteComponent
    ],
    imports: [
        CommonModule,
        SharedHomeModule
    ]
})
export class SearchSiteModule {
}
