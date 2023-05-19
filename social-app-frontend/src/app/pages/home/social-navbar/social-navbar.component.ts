import { Component, OnDestroy } from '@angular/core';
import { Subject, takeUntil } from "rxjs";
import { AuthService } from "@core/services/auth/auth.service";
import { UtilService } from "@services/utils/util.service";
import { UserService } from "@services/utils/user.service";
import { RoutePaths } from "@core/enums/RoutePaths";

@Component({
    selector: 'app-social-navbar',
    templateUrl: './social-navbar.component.html',
    styleUrls: ['./social-navbar.component.scss']
})
export class SocialNavbarComponent implements OnDestroy {
    private onDestroy$: Subject<void> = new Subject<void>();

    constructor(private authService: AuthService,
                private utilService: UtilService,
                private userService: UserService) {
    }

    logoutUserFromSite(): void {
        this.authService.logoutUser().pipe(takeUntil(this.onDestroy$)).subscribe((): void => {
            this.utilService.clearStorage();

            this.userService.setWasUserLoggedOut = true;

            this.utilService.navigate(RoutePaths.LOGIN_DIRECT);
        });
    }

    ngOnDestroy(): void {
        this.onDestroy$.next();
        this.onDestroy$.complete();
    }
}
