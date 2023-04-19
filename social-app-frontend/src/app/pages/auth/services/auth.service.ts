import { Injectable } from '@angular/core';
import { HttpClient } from "@angular/common/http";
import { AuthResponse } from "../../../core/data/auth-response";
import { RegisterRequest } from "../../../core/data/register-request";
import { Observable } from "rxjs";
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

    registerUser(registerRequest: RegisterRequest): void {
        const response: Observable<AuthResponse> = this.http.post<AuthResponse>(AuthApiCalls.REGISTER_URL, registerRequest);

        response.subscribe((data: AuthResponse) => {
            this.addData(data);
        });
        console.log((this.localStorageService.getValueFromStorage(StorageKeys.AUTH_TOKEN)));
        console.log(this.localStorageService.getValueFromStorage(StorageKeys.REFRESH_TOKEN));
    }

    loginUser(loginRequest: LoginRequest): void {
        const response: Observable<AuthResponse> = this.http.post<AuthResponse>(AuthApiCalls.LOGIN_URL, loginRequest);

        response.subscribe((data: AuthResponse) => {
            this.addData(data);

        });
        console.log((this.localStorageService.getValueFromStorage(StorageKeys.AUTH_TOKEN)));
        console.log(this.localStorageService.getValueFromStorage(StorageKeys.REFRESH_TOKEN));
    }

    refreshUsersToken(refreshToken: string): void {
        const response: Observable<AuthResponse> = this.http.post<AuthResponse>(AuthApiCalls.REFRESH_URL, refreshToken);

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

        this.localStorageService.addValueIntoStorage(StorageKeys.AUTH_TOKEN, data.authToken);
        this.localStorageService.addValueIntoStorage(StorageKeys.REFRESH_TOKEN, data.refreshToken);
    }
}
