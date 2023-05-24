import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FriendComponent } from "./friend/friend.component";
import { IconsModule } from "@icons/icons.module";
import { TextInputComponent } from './text-input/text-input.component';
import { FormsModule, ReactiveFormsModule } from "@angular/forms";
import { PostComponent } from './post/post.component';
import { SocialLinkComponent } from './social-link/social-link.component';
import { EditDialogComponent } from './edit-dialog/edit-dialog.component';
import { CommentListComponent } from './comment-list/comment-list.component';
import { CommentComponent } from './comment/comment.component';
import { MatListModule } from "@angular/material/list";
import { SocialNavbarComponent } from "@home/shared-home/social-navbar/social-navbar.component";
import { SharedAuthModule } from "@auth/shared/shared-auth.module";


@NgModule({
    declarations: [
        FriendComponent,
        TextInputComponent,
        PostComponent,
        SocialLinkComponent,
        EditDialogComponent,
        CommentListComponent,
        CommentComponent,
        SocialNavbarComponent
    ],
    imports: [
        CommonModule,
        IconsModule,
        FormsModule,
        ReactiveFormsModule,
        MatListModule,
        SharedAuthModule
    ],
    exports: [
        FriendComponent,
        TextInputComponent,
        PostComponent,
        SocialLinkComponent,
        SocialNavbarComponent
    ]
})
export class SharedHomeModule {
}
