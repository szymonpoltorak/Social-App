import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Component, Input } from '@angular/core';
import { SocialLinkComponent } from './social-link.component';
import { FriendComponent } from "@home/shared-home/friend/friend.component";
import { TextInputComponent } from "@home/shared-home/text-input/text-input.component";
import { PostComponent } from "@home/shared-home/post/post.component";
import { EditDialogComponent } from "@home/shared-home/edit-dialog/edit-dialog.component";
import { CommentListComponent } from "@home/shared-home/comment-list/comment-list.component";
import { CommentComponent } from "@home/shared-home/comment/comment.component";
import { SocialNavbarComponent } from "@home/shared-home/social-navbar/social-navbar.component";
import { CommonModule } from "@angular/common";
import { IconsModule } from "@icons/icons.module";
import { FormsModule, ReactiveFormsModule } from "@angular/forms";
import { MatListModule } from "@angular/material/list";
import { SharedAuthModule } from "@auth/shared/shared-auth.module";

@Component({ selector: 'app-test', template: '' })
class TestComponent {}

describe('SocialLinkComponent', () => {
    let component: SocialLinkComponent;
    let fixture: ComponentFixture<SocialLinkComponent>;

    beforeEach(async () => {
        await TestBed.configureTestingModule({
            declarations: [
                FriendComponent,
                TextInputComponent,
                PostComponent,
                SocialLinkComponent,
                EditDialogComponent,
                CommentListComponent,
                CommentComponent,
                SocialNavbarComponent,
                TestComponent
            ],
            imports: [
                CommonModule,
                IconsModule,
                FormsModule,
                ReactiveFormsModule,
                MatListModule,
                SharedAuthModule,
            ],
        }).compileComponents();
    });

    beforeEach(() => {
        fixture = TestBed.createComponent(SocialLinkComponent);
        component = fixture.componentInstance;
        fixture.detectChanges();
    });

    afterEach(() => {
        fixture.destroy();
    });

    it('should create the component', () => {
        expect(component).toBeTruthy();
    });

    it('should bind the socialLink input correctly', () => {
        component.socialLink = 'https://example.com';
        fixture.detectChanges();

        const anchorElement: HTMLAnchorElement = fixture.nativeElement.querySelector('a');
        expect(anchorElement.href).toBe('https://example.com');
    });

    it('should bind the socialSiteName input correctly', () => {
        component.socialSiteName = 'Twitter';
        fixture.detectChanges();

        const spanElement: HTMLSpanElement = fixture.nativeElement.querySelector('span');
        expect(spanElement.textContent).toBe('Twitter');
    });
});
