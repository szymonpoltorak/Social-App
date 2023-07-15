import { Injectable } from '@angular/core';
import { HttpClient } from "@angular/common/http";
import { catchError, Observable, of, tap } from "rxjs";
import { RegisterRequest } from "@data/register-request";
import { AuthResponse } from "@data/auth-response";
import { AuthApiCalls } from "@enums/AuthApiCalls";
import { StorageKeys } from "@enums/StorageKeys";
import { LoginRequest } from "@data/login-request";
import { TokenResponse } from "@data/token-response";
import { AuthInterface } from "@interfaces/auth/AuthInterface";
import { AuthConstants } from "@enums/AuthConstants";
import { environment } from "@environments/environment";
import { UtilService } from "@services/utils/util.service";

@Injectable({
    providedIn: 'root'
})
export class AuthService implements AuthInterface {
    constructor(private http: HttpClient,
                private utilService: UtilService) {
    }

    isUserAuthenticated(): Observable<TokenResponse> {
        const authToken: string = this.utilService.getKeyValuePairFromStorage(StorageKeys.AUTH_TOKEN);
        const refreshToken: string = this.utilService.getKeyValuePairFromStorage(StorageKeys.REFRESH_TOKEN);

        console.log(JSON.parse(`{${ authToken }, ${ refreshToken }}`));

        return this.http.post<TokenResponse>(`${ environment.httpBackend }${ AuthApiCalls.IS_USER_AUTHENTICATED }`,
            this.buildAuthRequest(authToken));
    }

    logoutUser(): Observable<any> {
        return this.http.post(`${ environment.httpBackend }${ AuthApiCalls.LOGOUT_URL }`, {});
    }

    registerUser(registerRequest: RegisterRequest): Observable<AuthResponse> {
        return this.http.post<AuthResponse>(`${ environment.httpBackend }${ AuthApiCalls.REGISTER_URL }`,
            registerRequest)
            .pipe(catchError(() => of(JSON.parse(AuthApiCalls.ERROR_FOUND))));
    }

    loginUser(loginRequest: LoginRequest): Observable<AuthResponse> {
        return this.http.post<AuthResponse>(`${ environment.httpBackend }${ AuthApiCalls.LOGIN_URL }`, loginRequest)
            .pipe(catchError(() => of(JSON.parse(AuthApiCalls.ERROR_FOUND))));
    }

    refreshUsersToken(refreshToken: string): Observable<AuthResponse> {
        return this.http.post<AuthResponse>(`${ environment.httpBackend }${ AuthApiCalls.REFRESH_URL }`,
            this.buildRefreshToken(refreshToken)).pipe(tap((response: AuthResponse) => {
            this.saveData(response);
        }));
    }

    saveData(data: AuthResponse): void {
        console.log(data);

        if (data.authToken === AuthConstants.NO_TOKEN || data.refreshToken === AuthConstants.NO_TOKEN) {
            return;
        }
        this.utilService.addValueToStorage(StorageKeys.AUTH_TOKEN, data.authToken);
        this.utilService.addValueToStorage(StorageKeys.REFRESH_TOKEN, data.refreshToken);
    }

    private buildAuthRequest(authToken: string) {
        console.log(JSON.parse(`{${ authToken }}`));

        return JSON.parse(`{${ authToken }}`);
    }

    private buildRefreshToken(refreshToken: string) {
        return JSON.parse(`{"refreshToken": "${ refreshToken }"}`);
    }
}
