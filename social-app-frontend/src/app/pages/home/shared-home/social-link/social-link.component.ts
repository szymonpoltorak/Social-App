import { Component, Input } from '@angular/core';

@Component({
    selector: 'app-social-link',
    templateUrl: './social-link.component.html',
    styleUrls: ['./social-link.component.scss']
})
export class SocialLinkComponent {
    @Input() socialLink !: string;
    @Input() socialSiteName !: string;
}
