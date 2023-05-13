import { Injectable } from '@angular/core';
import { HttpClient } from "@angular/common/http";
import { Observable } from "rxjs";
import { FriendData } from "@core/interfaces/home/FriendData";
import { UserData } from "@core/interfaces/home/UserData";
import { HomeApiCalls } from "@core/enums/HomeApiCalls";
import { environment } from "@environments/environment";

@Injectable({
    providedIn: 'root'
})
export class HomeService {
    constructor(private http: HttpClient) {
    }

    getFriendList(username: string): Observable<FriendData[]> {
        return this.http.get<FriendData[]>(`${environment.httpBackend}${HomeApiCalls.GET_FRIEND_LIST}`, {
            params: {
                "username": username
            }
        });
    }

    getUserData(username: string): Observable<UserData> {
        return this.http.get<UserData>(`${environment.httpBackend}${HomeApiCalls.GET_USERDATA}`, {
            params: {
                "username": username
            }
        });
    }
}
