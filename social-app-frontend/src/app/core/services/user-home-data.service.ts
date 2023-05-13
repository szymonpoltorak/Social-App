import { Injectable } from '@angular/core';
import { HttpClient } from "@angular/common/http";
import { Observable } from "rxjs";
import { environment } from "@environments/environment";

@Injectable({
    providedIn: 'root'
})
export class UserHomeDataService {
    constructor(private http: HttpClient) {
    }

    updateUsersData(username: string, updateData: string, url: string): Observable<string> {
        return this.http.patch<string>(`${environment.httpBackend}${url}`, {
            "username": username,
            "updateData": updateData
        });
    }

    manageUsersFriendEndpoints(username: string, friendsUsername: string, url: string): Observable<string> {
        return this.http.patch<string>(`${environment.httpBackend}${url}`, {
            "username": username,
            "friendsUsername": friendsUsername
        });
    }
}
