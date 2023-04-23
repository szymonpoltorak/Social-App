import { Injectable } from '@angular/core';
import { HttpClient } from "@angular/common/http";
import { catchError, map, Observable, of } from "rxjs";
import { LocalStorageService } from "./local-storage.service";
import { RegisterRequest } from "../data/register-request";
import { AuthResponse } from "../data/auth-response";
import { AuthApiCalls } from "../enums/AuthApiCalls";
import { StorageKeys } from "../enums/StorageKeys";
import { LoginRequest } from "../data/login-request";
import { TokenResponse } from "../data/token-response";
import { AuthInterface } from "../interfaces/AuthInterface";

@Injectable({
    providedIn: 'root'
})
export class AuthService implements AuthInterface{
    constructor(private http: HttpClient,
                private localStorageService: LocalStorageService) {
    }

    isUserAuthenticated(): Observable<boolean> {
        const authToken: string = this.localStorageService.getValueFromStorage(StorageKeys.AUTH_TOKEN);
        const refreshToken: string = this.localStorageService.getValueFromStorage(StorageKeys.REFRESH_TOKEN)

        if (authToken === "") {
            return of(false);
        }
        console.log(JSON.parse(`{${ authToken }, ${ refreshToken }}`));

        // const response: Observable<TokenResponse> = this.http.post<TokenResponse>(AuthApiCalls.IS_USER_AUTHENTICATED,
        return this.http.post<TokenResponse>(AuthApiCalls.IS_USER_AUTHENTICATED,
            this.buildAuthRequest(authToken))
            .pipe(
                map((data: TokenResponse): boolean => {
                    // console.log("I enter this place");

                    // if (!data.isAuthTokenValid) {
                    //     // console.log("I am getting here");
                    //     this.refreshUsersToken(refreshToken).then(() => {
                    //         return this.areTokensPresent();
                    //     });
                    //
                    //     // return this.areTokensPresent();
                    // }
                    // console.log("Returning true");
                    return true;
                }),
                catchError(() => of(false))
            );
    }

    registerUser(registerRequest: RegisterRequest): Observable<AuthResponse> {
        return this.http.post<AuthResponse>(AuthApiCalls.REGISTER_URL, registerRequest)
            .pipe(catchError(() => of(JSON.parse(AuthApiCalls.ERROR_FOUND))));
    }

    loginUser(loginRequest: LoginRequest): Observable<AuthResponse> {
        return this.http.post<AuthResponse>(AuthApiCalls.LOGIN_URL, loginRequest)
            .pipe(catchError(() => of(JSON.parse(AuthApiCalls.ERROR_FOUND))));
    }

    refreshUsersToken(refreshToken: string): Observable<AuthResponse> {
        return this.http.post<AuthResponse>(AuthApiCalls.REFRESH_URL,
            this.buildRefreshToken(refreshToken))
            .pipe(catchError(() => of(JSON.parse(AuthApiCalls.ERROR_FOUND))));
    }

    saveData(data: AuthResponse): void {
        console.log(data);

        if (data.authToken === "" || data.refreshToken === "") {
            return;
        }
        console.log(data.authToken);
        console.log(data.refreshToken);

        this.localStorageService.addValueIntoStorage(StorageKeys.AUTH_TOKEN, data.authToken);
        this.localStorageService.addValueIntoStorage(StorageKeys.REFRESH_TOKEN, data.refreshToken);
    }

    private buildAuthRequest(authToken: string) {
        console.log(JSON.parse(`{${ authToken }}`));

        return JSON.parse(`{${ authToken }}`);
    }

    private buildRefreshToken(refreshToken: string) {
        return JSON.parse(`{${ refreshToken }}`);
    }
}
