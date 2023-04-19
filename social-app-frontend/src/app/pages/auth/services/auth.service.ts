import { Injectable } from '@angular/core';
import { HttpClient } from "@angular/common/http";
import { AuthResponse } from "../../../core/data/auth-response";
import { RegisterRequest } from "../../../core/data/register-request";
import { catchError, Observable, of } from "rxjs";
import { LoginRequest } from "../../../core/data/login-request";
import { AuthApiCalls } from "../../../core/enums/AuthApiCalls";
import { LocalStorageService } from "./local-storage.service";
import { StorageKeys } from "../../../core/enums/StorageKeys";

@Injectable({
    providedIn: 'root'
})
export class AuthService {
    constructor(private http: HttpClient,
                private localStorageService: LocalStorageService) {
    }

    /**
     * Registers a new user by making a POST request to the authentication API's registration endpoint with the provided
     * registration request data. If the request is successful, the response data containing the user's authentication and
     * refresh tokens will be added to local storage.
     *
     * @param registerRequest The registration request data to send in the request body.
     */
    registerUser(registerRequest: RegisterRequest): void {
        const response: Observable<AuthResponse> = this.http.post<AuthResponse>(AuthApiCalls.REGISTER_URL, registerRequest)
            .pipe(catchError(() => of(JSON.parse(AuthApiCalls.ERROR_FOUND))));

        response.subscribe((data: AuthResponse) => {
            this.addData(data);
        });
        console.log((this.localStorageService.getValueFromStorage(StorageKeys.AUTH_TOKEN)));
        console.log(this.localStorageService.getValueFromStorage(StorageKeys.REFRESH_TOKEN));
    }

    /**
     * Logs in a user by making a POST request to the authentication API's login endpoint with the provided login request data.
     * If the request is successful, the response data containing the user's authentication and refresh tokens will be added to
     * local storage.
     *
     * @param loginRequest The login request data to send in the request body.
     */
    loginUser(loginRequest: LoginRequest): void {
        const response: Observable<AuthResponse> = this.http.post<AuthResponse>(AuthApiCalls.LOGIN_URL, loginRequest)
            .pipe(catchError(() => of(JSON.parse(AuthApiCalls.ERROR_FOUND))));

        response.subscribe((data: AuthResponse) => {
                this.addData(data);
        });
        console.log((this.localStorageService.getValueFromStorage(StorageKeys.AUTH_TOKEN)));
        console.log(this.localStorageService.getValueFromStorage(StorageKeys.REFRESH_TOKEN));
    }

    /**
     * Sends a POST request to the authentication API's refresh endpoint with the provided refresh token to obtain a new authentication
     * token. If the request is successful, the response data containing the new authentication and refresh tokens will be added to
     * local storage, and the current authentication and refresh tokens will be removed.
     *
     * @param refreshToken The refresh token to send in the request body.
     */
    refreshUsersToken(refreshToken: string): void {
        const response: Observable<AuthResponse> = this.http.post<AuthResponse>(AuthApiCalls.REFRESH_URL, refreshToken)
            .pipe(catchError(() => of(JSON.parse(AuthApiCalls.ERROR_FOUND))));

        response.subscribe((data: AuthResponse) => {
           this.localStorageService.removeValueFromStorage(StorageKeys.AUTH_TOKEN);
           this.localStorageService.removeValueFromStorage(StorageKeys.REFRESH_TOKEN);

           this.addData(data);
        });
        console.log((this.localStorageService.getValueFromStorage(StorageKeys.AUTH_TOKEN)));
        console.log(this.localStorageService.getValueFromStorage(StorageKeys.REFRESH_TOKEN));
    }

    private addData(data: AuthResponse) {
        console.log(data);

        if (data.isEmpty()) {
            return;
        }
        this.localStorageService.addValueIntoStorage(StorageKeys.AUTH_TOKEN, data.authToken);
        this.localStorageService.addValueIntoStorage(StorageKeys.REFRESH_TOKEN, data.refreshToken);
    }
}
