import { Component, OnDestroy } from '@angular/core';
import { Router } from "@angular/router";
import { AuthService } from "../../core/services/auth.service";
import { Subject, takeUntil } from "rxjs";
import { LocalStorageService } from "../../core/services/local-storage.service";
import { RoutePaths } from "../../core/enums/RoutePaths";
import { UserService } from "../../core/services/user.service";

@Component({
    selector: 'app-home',
    templateUrl: './home.component.html',
    styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnDestroy {
    private onDestroy$: Subject<void> = new Subject<void>();

    constructor(private router: Router,
                private authService: AuthService,
                private localStorage: LocalStorageService,
                private userService: UserService) {
    }

    logoutUserFromSite(): void {
        this.authService.logoutUser().pipe(takeUntil(this.onDestroy$)).subscribe(data => {
            this.localStorage.clearStorage();

            this.userService.setWasUserLoggedOut = true;

            this.router.navigateByUrl(RoutePaths.LOGIN_DIRECT);
        });
    }

    ngOnDestroy(): void {
        this.onDestroy$.next();
        this.onDestroy$.complete();
    }
}
