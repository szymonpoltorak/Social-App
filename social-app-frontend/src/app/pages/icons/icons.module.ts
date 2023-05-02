import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { GithubIconComponent } from './github-icon/github-icon.component';
import { TwitterIconComponent } from './twitter-icon/twitter-icon.component';
import { LinkedInIconComponent } from './linked-in-icon/linked-in-icon.component';
import { EditIconComponent } from "./edit-icon/edit-icon.component";
import { WorkIconComponent } from './work/work-icon.component';
import { LocationIconComponent } from './location-icon/location-icon.component';
import { LogoutIconComponent } from './logout-icon/logout-icon.component';
import { NotifyIconComponent } from './notify-icon/notify-icon.component';
import { SettingsIconComponent } from './settings-icon/settings-icon.component';
import { ChatIconComponent } from './chat-icon/chat-icon.component';
import { SearchIconComponent } from './search-icon/search-icon.component';


@NgModule({
    declarations: [
        GithubIconComponent,
        TwitterIconComponent,
        LinkedInIconComponent,
        EditIconComponent,
        WorkIconComponent,
        LocationIconComponent,
        LogoutIconComponent,
        NotifyIconComponent,
        SettingsIconComponent,
        ChatIconComponent,
        SearchIconComponent
    ],
    exports: [
        GithubIconComponent,
        LinkedInIconComponent,
        TwitterIconComponent,
        EditIconComponent,
        WorkIconComponent,
        LocationIconComponent,
        SearchIconComponent,
        ChatIconComponent,
        NotifyIconComponent,
        SettingsIconComponent,
        LogoutIconComponent
    ],
    imports: [
        CommonModule
    ]
})
export class IconsModule {
}
