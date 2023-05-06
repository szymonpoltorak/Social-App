import { Component, OnDestroy, OnInit } from '@angular/core';
import { HomeService } from "@core/services/home.service";

@Component({
    selector: 'app-home-profile',
    templateUrl: './home-profile.component.html',
    styleUrls: ['./home-profile.component.scss']
})
export class HomeProfileComponent implements OnInit, OnDestroy {
    constructor(private homeService: HomeService) {
    }

    ngOnInit(): void {
    }

    ngOnDestroy(): void {
    }
}
