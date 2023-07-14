import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { OauthComponent } from "@pages/oauth/oauth.component";
import { OauthRoutingModule } from "@pages/oauth/oauth-routing.module";


@NgModule({
    declarations: [
        OauthComponent
    ],
    imports: [
        CommonModule,
        OauthRoutingModule
    ],
    providers: [
    ]
})
export class OauthModule {
}
