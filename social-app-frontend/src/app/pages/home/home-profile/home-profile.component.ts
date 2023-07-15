import { Component, OnDestroy, OnInit } from '@angular/core';
import { HomeService } from "@core/services/home/home.service";
import { Subject, takeUntil } from "rxjs";
import { UserData } from "@core/interfaces/home/UserData";
import { SocialNames } from "@core/enums/SocialNames";
import { HomeDialogService } from "@core/services/home/home-dialog.service";
import { HomeApiCalls } from "@core/enums/HomeApiCalls";
import { HomeProfileInterface } from "@core/interfaces/home/HomeProfileInterface";

@Component({
    selector: 'app-home-profile',
    templateUrl: './home-profile.component.html',
    styleUrls: ['./home-profile.component.scss']
})
export class HomeProfileComponent implements OnInit, OnDestroy, HomeProfileInterface {
    userDataDestroy$: Subject<void> = new Subject<void>();
    destroyDialog$: Subject<void> = new Subject<void>();
    job: string = "";
    userLocation: string = "";
    numOfFriends: number = 0;
    fullName: string = "";
    twitter: string = "";
    github: string = "";
    linkedin: string = "";
    protected readonly SocialNames = SocialNames;
    protected readonly HomeApiCalls = HomeApiCalls;

    constructor(private homeService: HomeService,
                private homeDialogService: HomeDialogService) {
    }

    ngOnInit(): void {
        this.homeService.getUserData()
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
        this.homeDialogService.openMatDialogWindow(title, url)
            .pipe(takeUntil(this.destroyDialog$))
            .subscribe((data: string): void => {
                if (data === null) {
                    this.homeDialogService.closeDialog();

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

        this.destroyDialog$.next();
        this.destroyDialog$.complete();
    }
}
