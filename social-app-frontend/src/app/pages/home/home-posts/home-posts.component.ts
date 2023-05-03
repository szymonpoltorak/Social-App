import { Component } from '@angular/core';
import { PostInterface } from "../../../core/interfaces/home/PostInterface";

@Component({
    selector: 'app-home-posts',
    templateUrl: './home-posts.component.html',
    styleUrls: ['./home-posts.component.scss']
})
export class HomePostsComponent {
    posts: PostInterface[] = [
        {
            postAuthor: "Artur Korulczyk",
            postAuthorLocation: "Warsaw, Poland",
            postContent: "Whats up guys?",
            numOfLikes: 10,
            numOfComments: 5
        },
        {
            postAuthor: "Wojtek Lewandowski",
            postAuthorLocation: "Berlin, Germany",
            postContent: "Who wants to play basketball??",
            numOfLikes: 1,
            numOfComments: 10
        },
        {
            postAuthor: "Anna Szymborska",
            postAuthorLocation: "Wien, Austria",
            postContent: "Live is funny!",
            numOfLikes: 100,
            numOfComments: 5
        },
        {
            postAuthor: "Julia Dziekanowska",
            postAuthorLocation: "Kiev, Ukraine",
            postContent: "Yesterday I started a new work!",
            numOfLikes: 67,
            numOfComments: 13
        },
        {
            postAuthor: "Artur Korulczyk",
            postAuthorLocation: "Warsaw, Poland",
            postContent: "Whats up guys?",
            numOfLikes: 10,
            numOfComments: 5
        },
        {
            postAuthor: "Wojtek Lewandowski",
            postAuthorLocation: "Berlin, Germany",
            postContent: "Who wants to play basketball??",
            numOfLikes: 1,
            numOfComments: 10
        },
        {
            postAuthor: "Anna Szymborska",
            postAuthorLocation: "Wien, Austria",
            postContent: "Live is funny!",
            numOfLikes: 100,
            numOfComments: 5
        },
        {
            postAuthor: "Julia Dziekanowska",
            postAuthorLocation: "Kiev, Ukraine",
            postContent: "Yesterday I started a new work!",
            numOfLikes: 67,
            numOfComments: 13
        },
    ]
}
