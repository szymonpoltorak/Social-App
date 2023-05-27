import { Component, EventEmitter, Input, OnDestroy, Output } from '@angular/core';
import { Subject, takeUntil } from "rxjs";
import { AuthService } from "@core/services/auth/auth.service";
import { UtilService } from "@services/utils/util.service";
import { UserService } from "@services/utils/user.service";
import { RoutePaths } from "@core/enums/RoutePaths";
import { SearchService } from "@services/search/search.service";
import { SocialNavbarInterface } from "@interfaces/home/SocialNavbarInterface";

@Component({
    selector: 'app-social-navbar',
    templateUrl: './social-navbar.component.html',
    styleUrls: ['./social-navbar.component.scss']
})
export class SocialNavbarComponent implements OnDestroy, SocialNavbarInterface {
    private onDestroy$: Subject<void> = new Subject<void>();
    @Input() logoUrl: string = "";
    @Output() searchEvent: EventEmitter<void> = new EventEmitter<void>();
    @Output() columnEvent: EventEmitter<boolean> = new EventEmitter<boolean>();
    searchValue !: string;
    isMenuVisible : boolean = true;
    @Input() isOnHomeSite : boolean = false;
    areFriendsVisible: boolean = false;
    currentColumn: number = 0;

    constructor(private authService: AuthService,
                private utilService: UtilService,
                private userService: UserService,
                private searchService: SearchService) {
    }

    toggleMenu(): void {
        this.isMenuVisible = !this.isMenuVisible;
    }

    changeColumn(): void {
        this.areFriendsVisible = !this.areFriendsVisible;

        this.columnEvent.emit(this.areFriendsVisible);
    }

    logoutUserFromSite(): void {
        this.authService.logoutUser().pipe(takeUntil(this.onDestroy$)).subscribe((): void => {
            this.utilService.clearStorage();

            this.userService.setWasUserLoggedOut = true;

            this.utilService.navigate(RoutePaths.LOGIN_DIRECT);
        });
    }

    searchForUser(): void {
        this.searchService.searchPattern = this.searchValue;
        this.searchEvent.emit();
    }

    navigateToUrl(): void {
        if (this.logoUrl === "") {
            return;
        }
        this.utilService.navigate(this.logoUrl);
    }

    ngOnDestroy(): void {
        this.onDestroy$.next();
        this.onDestroy$.complete();
    }
}
