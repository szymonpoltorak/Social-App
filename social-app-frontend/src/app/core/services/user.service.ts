import { Injectable } from '@angular/core';

@Injectable({
    providedIn: 'root'
})
export class UserService {
    private isAuthenticated !: boolean;
    private wasLoggedOut !: boolean;

    constructor() {
    }

    get isUserAuthenticated(): boolean {
        return this.isAuthenticated;
    }

    get wasUserLoggedOut(): boolean {
        return this.wasLoggedOut;
    }

    set setWasUserLoggedOut(wasLoggedOut: boolean) {
        this.wasLoggedOut = wasLoggedOut;
    }

    set setUserAuthentication(isAuthenticated: boolean) {
        this.isAuthenticated = isAuthenticated;
    }
}
