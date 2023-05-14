import { Injectable } from '@angular/core';
import { HttpClient } from "@angular/common/http";
import { Observable } from "rxjs";
import { PostData } from "@core/interfaces/home/PostData";
import { environment } from "@environments/environment";
import { HomeApiCalls } from "@core/enums/HomeApiCalls";

@Injectable({
    providedIn: 'root'
})
export class PostService {
    private numOfSite: number = 0;

    constructor(private http: HttpClient) {
    }

    createNewPost(postContent: string, authorUsername: string): Observable<PostData> {
        console.log(`PostContent: ${postContent}\nAuthorUsername: ${authorUsername}`);

        return this.http.post<PostData>(`${environment.httpBackend}${HomeApiCalls.CREATE_POST}`, {
            "postContent": postContent,
            "authorUsername": authorUsername
        });
    }

    getListOfPosts(): Observable<PostData[]> {
        return this.http.get<PostData[]>(`${environment.httpBackend}${HomeApiCalls.POST_LIST}`, {
            params: {
                "numOfSite": this.numOfSite
            }
        });
    }

    addUserToFriends(username: string, friendsUsername: string): Observable<string> {
        return this.http.patch<string>(`${environment.httpBackend}${HomeApiCalls.ADD_FRIEND}`, {
            "username": username,
            "friendsUsername": friendsUsername
        });
    }

    incrementSiteNumber(): void {
        this.numOfSite = this.numOfSite + 1;
    }
}
