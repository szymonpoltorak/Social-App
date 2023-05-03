import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FriendComponent } from "./friend/friend.component";
import { IconsModule } from "@icons/icons.module";
import { TextInputComponent } from './text-input/text-input.component';
import { FormsModule } from "@angular/forms";
import { PostComponent } from './post/post.component';


@NgModule({
    declarations: [
        FriendComponent,
        TextInputComponent,
        PostComponent,
    ],
    imports: [
        CommonModule,
        IconsModule,
        FormsModule
    ],
    exports: [
        FriendComponent,
        TextInputComponent,
        PostComponent
    ]
})
export class SharedHomeModule {
}
