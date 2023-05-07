import { Component, OnDestroy, OnInit } from '@angular/core';
import { HomeService } from "@core/services/home.service";
import { UserHomeDataService } from "@core/services/user-home-data.service";
import { UtilService } from "@core/services/util.service";
import { StorageKeys } from "@core/enums/StorageKeys";
import { Subject, takeUntil } from "rxjs";
import { UserData } from "@core/interfaces/home/UserData";
import { SocialNames } from "../../../core/enums/SocialNames";

@Component({
    selector: 'app-home-profile',
    templateUrl: './home-profile.component.html',
    styleUrls: ['./home-profile.component.scss']
})
export class HomeProfileComponent implements OnInit, OnDestroy {
    private userDataDestroy$: Subject<any> = new Subject<any>();
    job!: string;
    userLocation!: string;
    numOfFriends!: number;
    fullName !: string;
    twitter: string = "";
    github: string = "";
    linkedin: string = "";

    constructor(private homeService: HomeService,
                private userDataService: UserHomeDataService,
                private utilService: UtilService) {
    }

    ngOnInit(): void {
        const username: string = this.utilService.getValueFromStorage(StorageKeys.USERNAME);

        this.homeService.getUserData(username.substring(1, username.length - 1))
            .pipe(takeUntil(this.userDataDestroy$))
            .subscribe((data: UserData): void => {
                console.log(data);

                this.job = data.job;
                this.fullName = data.fullName;
                this.numOfFriends = data.numOfFriends;
                this.userLocation = data.location;
                this.twitter = data.twitter;
                this.github = data.github;
                this.linkedin = data.linkedin;
            });
    }

    ngOnDestroy(): void {
        this.userDataDestroy$.complete();
    }

    protected readonly SocialNames = SocialNames;
}
