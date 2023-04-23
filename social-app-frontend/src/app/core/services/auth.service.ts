import { Injectable, OnDestroy } from '@angular/core';
import { HttpClient } from "@angular/common/http";
import { catchError, map, Observable, of, Subject, takeUntil } from "rxjs";
import { LocalStorageService } from "./local-storage.service";
import { RegisterRequest } from "../data/register-request";
import { AuthResponse } from "../data/auth-response";
import { AuthApiCalls } from "../enums/AuthApiCalls";
import { StorageKeys } from "../enums/StorageKeys";
import { LoginRequest } from "../data/login-request";
import { AuthInterface } from "../interfaces/AuthInterface";
import { TokenResponse } from "../data/token-response";

@Injectable({
    providedIn: 'root'
})
export class AuthService implements AuthInterface, OnDestroy {
    private refreshDestroy$: Subject<void> = new Subject<void>();
    private authDestroy$: Subject<void> = new Subject<void>();
    private isAuthDestroy$: Subject<void> = new Subject<void>();

    constructor(private http: HttpClient,
                private localStorageService: LocalStorageService) {
    }

    isUserAuthenticated(): boolean {
        const authToken: string = this.localStorageService.getValueFromStorage(StorageKeys.AUTH_TOKEN);
        const refreshToken: string = this.localStorageService.getValueFromStorage(StorageKeys.REFRESH_TOKEN);

        if (authToken === "") {
            return false;
        }
        const response: Observable<TokenResponse> = this.http.post<TokenResponse>(AuthApiCalls.IS_USER_AUTHENTICATED,
            this.buildAuthRequest(authToken, refreshToken))
            .pipe(
                catchError(() => of(JSON.parse(AuthApiCalls.TOKEN_ERROR_FOUND))),
                takeUntil(this.isAuthDestroy$)
            );
        let isAuthTokenValid: boolean = false;
        let isRefreshTokenValid: boolean = false;

        response.subscribe((data: TokenResponse): void => {
            isAuthTokenValid = data.isAuthTokenValid;
            isRefreshTokenValid = data.isRefreshTokenValid;
        });

        if (isAuthTokenValid) {
            return true;
        }
        if (!isRefreshTokenValid) {
            return false;
        }
        this.refreshUsersToken(refreshToken);

        return true;
    }

    registerUser(registerRequest: RegisterRequest): void {
        this.handleAuthRequest(registerRequest, AuthApiCalls.REGISTER_URL);
    }

    loginUser(loginRequest: LoginRequest): void {
        this.handleAuthRequest(loginRequest, AuthApiCalls.LOGIN_URL);
    }

    refreshUsersToken(refreshToken: string): void {
        const response: Observable<AuthResponse> = this.getProperObservable(this.buildRefreshToken(refreshToken),
            AuthApiCalls.REFRESH_URL, this.refreshDestroy$);

        response.subscribe((data: AuthResponse): void => {
            this.localStorageService.removeValueFromStorage(StorageKeys.AUTH_TOKEN);
            this.localStorageService.removeValueFromStorage(StorageKeys.REFRESH_TOKEN);

            this.addData(data);
        });
        console.log((this.localStorageService.getValueFromStorage(StorageKeys.AUTH_TOKEN)));
        console.log(this.localStorageService.getValueFromStorage(StorageKeys.REFRESH_TOKEN));
    }

    ngOnDestroy(): void {
        this.refreshDestroy$.next();
        this.refreshDestroy$.complete();

        this.authDestroy$.next();
        this.authDestroy$.complete();
    }

    private handleAuthRequest<T>(request: T, url: AuthApiCalls): void {
        const response: Observable<AuthResponse> = this.getProperObservable(request, url, this.authDestroy$);

        response.subscribe((data: AuthResponse): void => {
            this.addData(data);
        });
        console.log((this.localStorageService.getValueFromStorage(StorageKeys.AUTH_TOKEN)));
        console.log(this.localStorageService.getValueFromStorage(StorageKeys.REFRESH_TOKEN));
    }

    private getProperObservable<T>(request: T, url: AuthApiCalls, onDestroy: Subject<void>): Observable<AuthResponse> {
        return this.http.post<AuthResponse>(url, request)
            .pipe(
                map((data: AuthResponse) => Object.assign(new AuthResponse(), data)),
                catchError(() => of(JSON.parse(AuthApiCalls.AUTH_ERROR_FOUND))),
                takeUntil(onDestroy)
            );
    }

    private buildAuthRequest(authToken: string, refreshToken: string) {
        return JSON.parse(`{${ authToken }, ${ refreshToken }}`);
    }

    private buildRefreshToken(refreshToken: string) {
        return JSON.parse(`{${ refreshToken }`);
    }

    private addData(data: AuthResponse): void {
        console.log(data);

        if (data.isEmpty()) {
            return;
        }
        this.localStorageService.addValueIntoStorage(StorageKeys.AUTH_TOKEN, data.authToken);
        this.localStorageService.addValueIntoStorage(StorageKeys.REFRESH_TOKEN, data.refreshToken);
    }
}
