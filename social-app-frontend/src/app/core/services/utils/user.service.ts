import { Injectable } from '@angular/core';

@Injectable({
    providedIn: 'root'
})
export class UserService {
    private isAuthenticated !: boolean;
    private wasLoggedOut !: boolean;

    constructor() {
    }

    /**
     * Gets the flag indicating whether the user is authenticated.
     */
    get isUserAuthenticated(): boolean {
        return this.isAuthenticated;
    }

    /**
     * Gets the flag indicating whether the user was logged out.
     */
    get wasUserLoggedOut(): boolean {
        return this.wasLoggedOut;
    }

    /**
     * Sets the flag indicating whether the user was logged out.
     *
     * @param wasLoggedOut The value indicating whether the user was logged out.
     */
    set setWasUserLoggedOut(wasLoggedOut: boolean) {
        this.wasLoggedOut = wasLoggedOut;
    }

    /**
     * Sets the flag indicating whether the user is authenticated.
     *
     * @param isAuthenticated The value indicating whether the user is authenticated.
     */
    set setUserAuthentication(isAuthenticated: boolean) {
        this.isAuthenticated = isAuthenticated;
    }
}
