import { Injectable } from '@angular/core';
import { UtilService } from "@services/utils/util.service";
import { StorageKeys } from "@enums/StorageKeys";

@Injectable({
    providedIn: 'root'
})
export class UserService {
    private isAuthenticated !: boolean;
    private wasLoggedOut !: boolean;

    constructor(private utilService: UtilService) {
    }

    /**
     * Gets the flag indicating whether the user is authenticated.
     */
    get isUserAuthenticated(): boolean {
        if (!this.isAuthenticated) {
            this.isAuthenticated = !!this.utilService.getValueFromStorage(StorageKeys.AUTH_TOKEN);

            return this.isAuthenticated;
        }
        return true;
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
