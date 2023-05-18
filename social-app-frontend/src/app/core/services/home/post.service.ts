import { Injectable } from '@angular/core';
import { HttpClient } from "@angular/common/http";
import { Observable } from "rxjs";
import { PostData } from "@core/interfaces/home/PostData";
import { environment } from "@environments/environment";
import { HomeApiCalls } from "@core/enums/HomeApiCalls";
import { LikeResponse } from "@core/interfaces/home/LikeResponse";
import { PostServiceInterface } from "@core/interfaces/home/PostServiceInterface";

@Injectable({
    providedIn: 'root'
})
export class PostService implements PostServiceInterface {
    private numOfSite: number = 0;

    constructor(private http: HttpClient) {
    }

    createNewPost(postContent: string): Observable<PostData> {
        return this.http.post<PostData>(`${ environment.httpBackend }${ HomeApiCalls.CREATE_POST }`, {}, {
            params: {
                "postContent": postContent
            }
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
        return this.http.patch<string>(`${ environment.httpBackend }${ url }`, {}, {
            params: {
                "friendsUsername": friendsUsername
            }
        });
    }

    updateNumOfLikes(postId: number): Observable<LikeResponse> {
        return this.http.patch<LikeResponse>(`${ environment.httpBackend }${ HomeApiCalls.UPDATE_LIKES }`, {}, {
            params: {
                "postId": postId
            }
        });
    }

    deletePost(postId: number): Observable<void> {
        return this.http.delete<void>(`${ environment.httpBackend }${ HomeApiCalls.DELETE_POST }`, {
            params: {
                "postId": postId
            }
        });
    }

    incrementSiteNumber(): void {
        this.numOfSite = this.numOfSite + 1;
    }
}