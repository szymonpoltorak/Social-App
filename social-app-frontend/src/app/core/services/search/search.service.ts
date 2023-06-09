import { Injectable } from '@angular/core';
import { HttpClient } from "@angular/common/http";
import { Observable } from "rxjs";
import { UserSearchData } from "@interfaces/home/UserSearchData";
import { environment } from "@environments/environment";
import { SearchApiCalls } from "@enums/SearchApiCalls";
import { SearchServiceInterface } from "@interfaces/search/SearchServiceInterface";

@Injectable({
    providedIn: 'root'
})
export class SearchService implements SearchServiceInterface {
    searchPattern !: string;

    constructor(private http: HttpClient) {
    }

    getListOfUsersOfPattern(numOfSite: number): Observable<UserSearchData[]> {
        return this.http.get<UserSearchData[]>(`${ environment.httpBackend }${ SearchApiCalls.USERS_LIST_PATTERN }`, {
            params: {
                "pattern": this.searchPattern,
                "numOfSite": numOfSite
            }
        });
    }
}
