import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { HomeRoutingModule } from './home-routing.module';
import { HomeComponent } from './home.component';
import { SharedAuthModule } from "../auth/shared/shared-auth.module";
import { SocialNavbarComponent } from './social-navbar/social-navbar.component';
import { HomeProfileComponent } from './home-profile/home-profile.component';
import { HomePostsComponent } from './home-posts/home-posts.component';
import { HomeFriendsComponent } from './home-friends/home-friends.component';
import { MatListModule } from "@angular/material/list";
import { IconsModule } from "../icons/icons.module";
import { SharedHomeModule } from "./shared-home/shared-home.module";
import { PostButtonBarComponent } from './post-button-bar/post-button-bar.component';


@NgModule({
    declarations: [
        HomeComponent,
        SocialNavbarComponent,
        HomeProfileComponent,
        HomePostsComponent,
        HomeFriendsComponent,
        PostButtonBarComponent,
    ],
    imports: [
        CommonModule,
        HomeRoutingModule,
        SharedHomeModule,
        MatListModule,
        IconsModule,
        SharedAuthModule
    ]
})
export class HomeModule {
}
