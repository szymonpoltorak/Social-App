import { Injectable } from '@angular/core';
import { HttpClient } from "@angular/common/http";
import { Observable } from "rxjs";
import { Friend } from "@core/interfaces/home/Friend";
import { UserData } from "@core/interfaces/home/UserData";

@Injectable({
    providedIn: 'root'
})
export class HomeService {
    constructor(private http: HttpClient) {
    }

    getFriendList(username: string): Observable<Friend[]> {
        return this.http.get<Friend[]>("/api/home/friendList", {
            params: {
                "username": username
            }
        });
    }

    getUserData(username: string): Observable<UserData> {
        return this.http.get<UserData>("/api/home/userData", {
            params: {
                "username": username
            }
        })
    }
}
