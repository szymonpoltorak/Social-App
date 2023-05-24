import { Injectable } from '@angular/core';
import { HttpClient } from "@angular/common/http";
import { Observable } from "rxjs";
import { UserSearchData } from "@interfaces/home/UserSearchData";
import { environment } from "@environments/environment";
import { SearchApiCalls } from "@enums/SearchApiCalls";

@Injectable({
    providedIn: 'root'
})
export class SearchService {
    searchPattern !: string;
    numOfSite: number = 0;

    constructor(private http: HttpClient) {
    }

    getListOfUsersOfPattern(): Observable<UserSearchData[]> {
        return this.http.get<UserSearchData[]>(`${ environment.httpBackend }${ SearchApiCalls.USERS_LIST_PATTERN }`, {
            params: {
                "pattern": this.searchPattern,
                "numOfSite": this.numOfSite
            }
        });
    }
}
