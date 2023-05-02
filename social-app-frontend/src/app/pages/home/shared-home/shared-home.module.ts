import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FriendComponent } from "./friend/friend.component";
import { IconsModule } from "../../icons/icons.module";
import { TextInputComponent } from './text-input/text-input.component';
import { FormsModule } from "@angular/forms";


@NgModule({
    declarations: [
        FriendComponent,
        TextInputComponent,
    ],
    imports: [
        CommonModule,
        IconsModule,
        FormsModule
    ],
    exports: [
        FriendComponent,
        TextInputComponent
    ]
})
export class SharedHomeModule {
}
