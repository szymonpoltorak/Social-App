import { Injectable } from '@angular/core';
import { HttpEvent, HttpHandler, HttpInterceptor, HttpRequest } from '@angular/common/http';
import { catchError, Observable, throwError } from 'rxjs';
import { Router } from "@angular/router";
import { UserService } from "../services/user.service";
import { LocalStorageService } from "../services/local-storage.service";
import { RoutePaths } from "../enums/RoutePaths";

@Injectable()
export class AuthInterceptor implements HttpInterceptor {
    constructor(private router: Router,
                private userService: UserService,
                private localStorageService: LocalStorageService) {
    }

    intercept(request: HttpRequest<unknown>, next: HttpHandler): Observable<HttpEvent<unknown>> {
        return next.handle(request).pipe(
            catchError((error: any) => {
                if (error.status === 401) {
                    this.router.navigateByUrl(RoutePaths.LOGIN_DIRECT);
                    this.userService.setWasUserLoggedOut = true;

                    this.localStorageService.clearStorage();
                }
                return throwError(error);
            })
        );
    }
}
