import { Injectable } from '@angular/core';
import { HttpClient } from "@angular/common/http";
import { RegisterResponse } from "../../../core/data/register-response";
import { RegisterRequest } from "../../../core/data/register-request";
import { Observable } from "rxjs";
import { LoginRequest } from "../../../core/data/login-request";
import { LoginResponse } from "../../../core/data/login-response";
import { AuthApiCalls } from "../../../core/enums/AuthApiCalls";

@Injectable({
    providedIn: 'root'
})
export class AuthService {
    constructor(private http: HttpClient) {
    }

    registerUser(registerRequest: RegisterRequest): Observable<RegisterResponse> {
        return this.http.post<RegisterResponse>(AuthApiCalls.REGISTER_URL, registerRequest);
    }

    loginUser(loginRequest: LoginRequest): Observable<LoginResponse> {
        return this.http.post<LoginResponse>(AuthApiCalls.LOGIN_URL, loginRequest);
    }

    refreshUsersToken(refreshToken: string): void {
        this.http.post(AuthApiCalls.REFRESH_URL, refreshToken);
    }
}
