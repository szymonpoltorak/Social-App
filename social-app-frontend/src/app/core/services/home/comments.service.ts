import { Injectable } from '@angular/core';
import { HttpClient } from "@angular/common/http";
import { Observable } from "rxjs";
import { CommentData } from "@interfaces/home/CommentData";
import { environment } from "@environments/environment";
import { HomeApiCalls } from "@enums/HomeApiCalls";
import { LikeResponse } from "@interfaces/home/LikeResponse";
import { CommentsServiceInterface } from "@interfaces/home/CommentsServiceInterface";

@Injectable({
    providedIn: 'root'
})
export class CommentsService implements CommentsServiceInterface {
    constructor(private http: HttpClient) {
    }

    updateCommentNumOfLikes(commentId: number): Observable<LikeResponse> {
        return this.http.patch<LikeResponse>(`${ environment.httpBackend }${ HomeApiCalls.LIKE_COMMENT }`, {}, {
            params: {
                "commentId": commentId
            }
        });
    }

    getListOfComments(postId: number, numOfSite: number): Observable<CommentData[]> {
        return this.http.get<CommentData[]>(`${ environment.httpBackend }${ HomeApiCalls.COMMENT_LIST }`, {
            params: {
                "postId": postId,
                "numOfSite": numOfSite
            }
        });
    }

    createComment(commentContent: string, postId: number): Observable<CommentData> {
        return this.http.post<CommentData>(`${ environment.httpBackend }${ HomeApiCalls.CREATE_COMMENT }`, {
            "commentContent": commentContent,
            "postId": postId
        });
    }
}
