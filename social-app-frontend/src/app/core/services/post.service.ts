import { Injectable } from '@angular/core';
import { HttpClient } from "@angular/common/http";
import { Observable } from "rxjs";
import { PostData } from "@core/interfaces/home/PostData";
import { environment } from "@environments/environment";
import { HomeApiCalls } from "@core/enums/HomeApiCalls";
import { LikeResponse } from "@core/interfaces/home/LikeResponse";

@Injectable({
    providedIn: 'root'
})
export class PostService {
    private numOfSite: number = 0;

    constructor(private http: HttpClient) {
    }

    createNewPost(postContent: string): Observable<PostData> {
        return this.http.post<PostData>(`${ environment.httpBackend }${ HomeApiCalls.CREATE_POST }`, {
            "postContent": postContent
        });
    }

    getListOfPosts(): Observable<PostData[]> {
        return this.http.get<PostData[]>(`${ environment.httpBackend }${ HomeApiCalls.POST_LIST }`, {
            params: {
                "numOfSite": this.numOfSite
            }
        });
    }

    manageFriendStatus(friendsUsername: string, url: string): Observable<string> {
        return this.http.patch<string>(`${ environment.httpBackend }${ url }`, {
            "friendsUsername": friendsUsername
        });
    }

    updateNumOfLikes(postId: number): Observable<LikeResponse> {
        return this.http.patch<LikeResponse>(`${ environment.httpBackend }${ HomeApiCalls.UPDATE_LIKES }`, {
            "postId": postId
        });
    }

    incrementSiteNumber(): void {
        this.numOfSite = this.numOfSite + 1;
    }
}
