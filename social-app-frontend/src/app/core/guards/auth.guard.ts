import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot, UrlTree } from '@angular/router';
import { Observable } from 'rxjs';
import { AuthService } from "../services/auth.service";

@Injectable({
    providedIn: 'root'
})
export class AuthGuard implements CanActivate{
    constructor(private authService: AuthService,
                private router: Router) {
    }

    canActivate(
        route: ActivatedRouteSnapshot,
        state: RouterStateSnapshot): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {

        return true;
        // return this.authService.isUserAuthenticated().pipe(
        //     map((isAuthenticated: boolean): UrlTree | boolean => {
        //         if (!isAuthenticated) {
        //             return this.router.createUrlTree(["/auth/login"]);
        //         }
        //         return true;
        //     }),
        //     takeUntil(this.onDestroy$)
        // );
        // this.authService.isUserAuthenticated().subscribe((isAuthenticated: boolean): void => {
        //    if (!isAuthenticated) {
        //        this.router.createUrlTree(["/auth/login"]);
        //    }
        // });
        // if (!this.authService.isUserAuthenticated()) {
        //     return this.router.createUrlTree(["/auth/login"]);
        // }
        // return true;
    }
}
