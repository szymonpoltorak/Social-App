import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FriendComponent } from "./friend/friend.component";
import { IconsModule } from "@icons/icons.module";
import { TextInputComponent } from './text-input/text-input.component';
import { FormsModule, ReactiveFormsModule } from "@angular/forms";
import { PostComponent } from './post/post.component';
import { SocialLinkComponent } from './social-link/social-link.component';
import { EditDialogComponent } from './edit-dialog/edit-dialog.component';


@NgModule({
    declarations: [
        FriendComponent,
        TextInputComponent,
        PostComponent,
        SocialLinkComponent,
        EditDialogComponent,
    ],
    imports: [
        CommonModule,
        IconsModule,
        FormsModule,
        ReactiveFormsModule
    ],
    exports: [
        FriendComponent,
        TextInputComponent,
        PostComponent,
        SocialLinkComponent
    ]
})
export class SharedHomeModule {
}
