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
import { UserIconComponent } from './user-icon/user-icon.component';
import { AddFriendIconComponent } from './add-friend-icon/add-friend-icon.component';
import { RemoveFriendIconComponent } from './remove-friend-icon/remove-friend-icon.component';
import { LikeIconComponent } from './like-icon/like-icon.component';
import { CommentIconComponent } from './comment-icon/comment-icon.component';
import { ShareIconComponent } from './share-icon/share-icon.component';
import { DeleteIconComponent } from './delete-icon/delete-icon.component';
import { SendIconComponent } from './send-icon/send-icon.component';


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
        SearchIconComponent,
        UserIconComponent,
        AddFriendIconComponent,
        RemoveFriendIconComponent,
        LikeIconComponent,
        CommentIconComponent,
        ShareIconComponent,
        DeleteIconComponent,
        SendIconComponent
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
        LogoutIconComponent,
        UserIconComponent,
        RemoveFriendIconComponent,
        AddFriendIconComponent,
        LikeIconComponent,
        CommentIconComponent,
        ShareIconComponent,
        DeleteIconComponent,
        SendIconComponent
    ],
    imports: [
        CommonModule
    ]
})
export class IconsModule {
}
