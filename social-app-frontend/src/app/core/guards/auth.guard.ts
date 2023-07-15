import { Injectable } from '@angular/core';
import { Router, UrlTree } from '@angular/router';
import { Observable } from 'rxjs';
import { UserService } from "@services/utils/user.service";
import { RoutePaths } from "@enums/RoutePaths";

@Injectable({
    providedIn: 'root'
})
export class AuthGuard {
    constructor(private userService: UserService,
                private router: Router) {
    }

    canActivate(): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {
        if (!this.userService.isUserAuthenticated) {
            return this.router.createUrlTree([RoutePaths.LOGIN_DIRECT]);
        }
        return true;
    }
}
