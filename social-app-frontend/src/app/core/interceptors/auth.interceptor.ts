import { Injectable } from '@angular/core';
import {
    HttpErrorResponse,
    HttpEvent,
    HttpHandler,
    HttpInterceptor,
    HttpRequest,
    HttpStatusCode
} from '@angular/common/http';
import { BehaviorSubject, catchError, filter, Observable, switchMap, take, throwError } from 'rxjs';
import { Router } from "@angular/router";
import { UserService } from "@services/utils/user.service";
import { UtilService } from "@services/utils/util.service";
import { AuthService } from "@services/auth/auth.service";
import { StorageKeys } from "@enums/StorageKeys";
import { AuthResponse } from "@data/auth-response";

@Injectable()
export class AuthInterceptor implements HttpInterceptor {
    private isRefreshing: boolean = false;
    private refreshTokenSubject: BehaviorSubject<any> = new BehaviorSubject<any>(null);


    constructor(private router: Router,
                private userService: UserService,
                private utilService: UtilService,
                private authService: AuthService) {
    }

    intercept(request: HttpRequest<unknown>, next: HttpHandler): Observable<HttpEvent<unknown>> {
        const key: StorageKeys = this.isRefreshing ? StorageKeys.REFRESH_TOKEN : StorageKeys.AUTH_TOKEN;
        const token: string = this.utilService.getValueFromStorage(key);

        return next.handle(this.addTokenToRequest(token, request)).pipe(
            catchError((error: any) => {
                if (error instanceof HttpErrorResponse && error.status === HttpStatusCode.Forbidden) {
                    this.utilService.removeValueFromStorage(StorageKeys.AUTH_TOKEN);

                    return this.refreshUsersTokenIfPossible(request, next);
                }
                return throwError(error);
            })
        );
    }

    private refreshUsersTokenIfPossible(request: HttpRequest<unknown>,
                                        next: HttpHandler): Observable<HttpEvent<unknown>> {
        if (!this.isRefreshing) {
            this.isRefreshing = true;
            this.refreshTokenSubject.next(null);

            return this.authService.refreshUsersToken(this.utilService.getValueFromStorage(StorageKeys.REFRESH_TOKEN))
                .pipe(
                    switchMap((data: AuthResponse) => {
                        this.isRefreshing = false;
                        this.refreshTokenSubject.next(data.authToken);

                        return next.handle(this.addTokenToRequest(data.authToken, request));
                    })
                );

        }
        return this.refreshTokenSubject.pipe(
            filter(token => token != null),
            take(1),
            switchMap((authToken: string) => {
                return next.handle(this.addTokenToRequest(authToken, request));
            }));
    }

    private addTokenToRequest(token: string, request: HttpRequest<unknown>): HttpRequest<unknown> {
        return request.clone({
            setHeaders: {
                Authorization: `Bearer ${ token }`
            }
        });
    }
}
