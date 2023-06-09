import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { HomeRoutingModule } from './home-routing.module';
import { HomeComponent } from './home.component';
import { HomeProfileComponent } from './home-profile/home-profile.component';
import { HomePostsComponent } from './home-posts/home-posts.component';
import { HomeFriendsComponent } from './home-friends/home-friends.component';
import { MatListModule } from "@angular/material/list";
import { IconsModule } from "@icons/icons.module";
import { SharedHomeModule } from "./shared-home/shared-home.module";
import { PostButtonBarComponent } from './post-button-bar/post-button-bar.component';
import { HomeDialogService } from "@core/services/home/home-dialog.service";


@NgModule({
    declarations: [
        HomeComponent,
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
    ],
    providers: [
        HomeDialogService
    ]
})
export class HomeModule {
}
