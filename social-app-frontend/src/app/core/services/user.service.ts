import { Injectable } from '@angular/core';

@Injectable({
    providedIn: 'root'
})
export class UserService {
    private isAuthenticated !: boolean;

    constructor() {
    }

    get isUserAuthenticated(): boolean {
        return this.isAuthenticated;
    }

    authenticateUser(): void {
        this.isAuthenticated = true;
    }
}
