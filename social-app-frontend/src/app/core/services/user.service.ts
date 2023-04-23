import { Injectable } from '@angular/core';

@Injectable({
    providedIn: 'root'
})
export class UserService {
    isUserAuthenticated !: boolean;

    constructor() {
    }
}
