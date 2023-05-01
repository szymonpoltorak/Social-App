import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { HomeRoutingModule } from './home-routing.module';
import { HomeComponent } from './home.component';
import { SharedModule } from "../auth/shared/shared.module";
import { SocialNavbarComponent } from './social-navbar/social-navbar.component';


@NgModule({
    declarations: [
        HomeComponent,
        SocialNavbarComponent
    ],
    imports: [
        CommonModule,
        HomeRoutingModule,
        SharedModule,
    ]
})
export class HomeModule {
}
