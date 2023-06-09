import { Injectable } from '@angular/core';
import { HttpClient } from "@angular/common/http";
import { Observable } from "rxjs";
import { environment } from "@environments/environment";
import { UserHomeInterface } from "@core/interfaces/home/UserHomeInterface";

@Injectable({
    providedIn: 'root'
})
export class UserHomeDataService implements UserHomeInterface {
    constructor(private http: HttpClient) {
    }

    updateUsersData(updateData: string, url: string): Observable<string> {
        return this.http.patch<string>(`${ environment.httpBackend }${ url }`, {}, {
            params: {
                "updateData": updateData
            }
        });
    }

    manageUsersFriendEndpoints(friendsUsername: string, url: string): Observable<string> {
        return this.http.patch<string>(`${ environment.httpBackend }${ url }`, {}, {
            params: {
                "friendsUsername": friendsUsername
            }
        });
    }
}
