import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FriendComponent } from "./friend/friend.component";
import { IconsModule } from "../../icons/icons.module";


@NgModule({
    declarations: [
        FriendComponent,
    ],
    imports: [
        CommonModule,
        IconsModule
    ],
    exports: [
        FriendComponent
    ]
})
export class SharedHomeModule {
}
