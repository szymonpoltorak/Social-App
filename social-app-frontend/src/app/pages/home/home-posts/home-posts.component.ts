import { Component, OnDestroy, OnInit } from '@angular/core';
import { PostData } from "@core/interfaces/home/PostData";
import { PostService } from "@core/services/post.service";
import { Subject, takeUntil } from "rxjs";

@Component({
    selector: 'app-home-posts',
    templateUrl: './home-posts.component.html',
    styleUrls: ['./home-posts.component.scss']
})
export class HomePostsComponent implements OnInit, OnDestroy {
    private destroyPostList$: Subject<void> = new Subject<void>();
    posts: PostData[] = [];

    constructor(private postService: PostService) {
    }

    ngOnInit(): void {
        this.postService.getListOfPosts()
            .pipe(takeUntil(this.destroyPostList$))
            .subscribe((data: PostData[]): void => {
                console.log(data);
                this.posts = data;
            });
    }

    ngOnDestroy(): void {
        this.destroyPostList$.next();
        this.destroyPostList$.complete();
    }
}
