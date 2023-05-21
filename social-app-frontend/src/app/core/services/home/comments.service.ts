import { Injectable } from '@angular/core';
import { HttpClient } from "@angular/common/http";
import { Observable } from "rxjs";
import { CommentData } from "@interfaces/home/CommentData";
import { environment } from "@environments/environment";
import { HomeApiCalls } from "@enums/HomeApiCalls";

@Injectable({
    providedIn: 'root'
})
export class CommentsService {
    constructor(private http: HttpClient) {
    }

    getListOfComments(postId: number, numOfSite: number): Observable<CommentData[]> {
        return this.http.get<CommentData[]>(`${environment.httpBackend}${HomeApiCalls.COMMENT_LIST}`, {
            params: {
                "postId": postId,
                "numOfSite": numOfSite
            }
        });
    }

    createComment(commentContent: string, postId: number): Observable<CommentData> {
        return this.http.post<CommentData>(`${environment.httpBackend}${HomeApiCalls.CREATE_COMMENT}`, {
            "commentContent": commentContent,
            "postId": postId
        });
    }
}
