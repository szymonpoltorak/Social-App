import { Injectable } from '@angular/core';
import { HttpEvent, HttpHandler, HttpInterceptor, HttpRequest } from '@angular/common/http';
import { catchError, Observable, throwError } from 'rxjs';
import { Router } from "@angular/router";
import { UserService } from "@services/utils/user.service";
import { RoutePaths } from "@enums/RoutePaths";
import { StorageKeys } from "@enums/StorageKeys";
import { UtilService } from "@services/utils/util.service";

@Injectable()
export class AuthInterceptor implements HttpInterceptor {
    constructor(private router: Router,
                private userService: UserService,
                private utilService: UtilService) {
    }

    intercept(request: HttpRequest<unknown>, next: HttpHandler): Observable<HttpEvent<unknown>> {
        const header: string = this.utilService.getValueFromStorage(StorageKeys.AUTH_TOKEN);

        request = request.clone({
            setHeaders: {
                "Authorization": `Bearer ${ header.substring(1, header.length - 1) }`
            }
        });

        return next.handle(request).pipe(
            catchError((error: any) => {
                if (error.status === 401) {
                    this.router.navigateByUrl(RoutePaths.LOGIN_DIRECT);
                    this.userService.setWasUserLoggedOut = true;

                    this.utilService.clearStorage();
                }
                return throwError(error);
            })
        );
    }
}
