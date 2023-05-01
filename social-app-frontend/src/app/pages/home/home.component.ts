import { Component, OnDestroy } from '@angular/core';
import { AuthService } from "../../core/services/auth.service";
import { Subject, takeUntil } from "rxjs";
import { RoutePaths } from "../../core/enums/RoutePaths";
import { UserService } from "../../core/services/user.service";
import { UtilService } from "../../core/services/util.service";

@Component({
    selector: 'app-home',
    templateUrl: './home.component.html',
    styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnDestroy {
    private onDestroy$: Subject<void> = new Subject<void>();

    constructor(private authService: AuthService,
                private utilService: UtilService,
                private userService: UserService) {
    }

    logoutUserFromSite(): void {
        this.authService.logoutUser().pipe(takeUntil(this.onDestroy$)).subscribe( () => {
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
