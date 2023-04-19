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
        const response: Observable<RegisterResponse> = this.http.post<RegisterResponse>(AuthApiCalls.REGISTER_URL, registerRequest);

        response.subscribe(data => console.log(data));

        return response;
    }

    loginUser(loginRequest: LoginRequest): Observable<LoginResponse> {
        const response: Observable<LoginResponse> = this.http.post<LoginResponse>(AuthApiCalls.LOGIN_URL, loginRequest);

        response.subscribe(data => console.log(data));

        return response;
    }

    refreshUsersToken(refreshToken: string): void {
        this.http.post(AuthApiCalls.REFRESH_URL, refreshToken);
    }
}
