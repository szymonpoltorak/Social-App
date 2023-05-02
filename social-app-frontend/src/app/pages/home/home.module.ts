import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { HomeRoutingModule } from './home-routing.module';
import { HomeComponent } from './home.component';
import { SharedModule } from "../auth/shared/shared.module";
import { SocialNavbarComponent } from './social-navbar/social-navbar.component';
import { HomeProfileComponent } from './home-profile/home-profile.component';
import { HomePostsComponent } from './home-posts/home-posts.component';
import { HomeFriendsComponent } from './home-friends/home-friends.component';
import { MatListModule } from "@angular/material/list";
import { IconsModule } from "../icons/icons.module";


@NgModule({
    declarations: [
        HomeComponent,
        SocialNavbarComponent,
        HomeProfileComponent,
        HomePostsComponent,
        HomeFriendsComponent,
    ],
    imports: [
        CommonModule,
        HomeRoutingModule,
        SharedModule,
        MatListModule,
        IconsModule
    ]
})
export class HomeModule {
}
