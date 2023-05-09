import { Component, OnDestroy, OnInit } from '@angular/core';
import { HomeService } from "@core/services/home.service";
import { UserHomeDataService } from "@core/services/user-home-data.service";
import { UtilService } from "@core/services/util.service";
import { StorageKeys } from "@core/enums/StorageKeys";
import { Subject, takeUntil } from "rxjs";
import { UserData } from "@core/interfaces/home/UserData";
import { SocialNames } from "@core/enums/SocialNames";
import { HomeDialogService } from "@core/services/home-dialog.service";
import { HomeApiCalls } from "@core/enums/HomeApiCalls";

@Component({
    selector: 'app-home-profile',
    templateUrl: './home-profile.component.html',
    styleUrls: ['./home-profile.component.scss']
})
export class HomeProfileComponent implements OnInit, OnDestroy {
    private userDataDestroy$: Subject<void> = new Subject<void>();
    private userDataDestroyDialog$: Subject<void> = new Subject<void>();
    protected readonly SocialNames = SocialNames;
    protected readonly HomeApiCalls = HomeApiCalls;
    job!: string;
    userLocation!: string;
    numOfFriends!: number;
    fullName !: string;
    twitter: string = "";
    github: string = "";
    linkedin: string = "";

    constructor(private homeService: HomeService,
                private userDataService: UserHomeDataService,
                private utilService: UtilService,
                private homeDialogService: HomeDialogService) {
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

    openDialog(title: string, url: string): void {
        this.homeDialogService.openMatDialogWindow(title, url).subscribe((data: string): void => {
            if (data === null) {
                return;
            }
            if (title === SocialNames.JOB_DIALOG) {
                this.job = data;
            }
            if (title === SocialNames.LOCATION_DIALOG) {
                this.userLocation = data;
            }
            if (title === SocialNames.TWITTER_DIALOG) {
                this.twitter = data;
            }
            if (title === SocialNames.GITHUB_DIALOG) {
                this.github = data;
            }
            if (title === SocialNames.LINKEDIN_DIALOG) {
                this.linkedin = data;
            }
            this.homeDialogService.closeDialog();
        });
    }

    ngOnDestroy(): void {
        this.userDataDestroy$.next();
        this.userDataDestroy$.complete();
    }
}
