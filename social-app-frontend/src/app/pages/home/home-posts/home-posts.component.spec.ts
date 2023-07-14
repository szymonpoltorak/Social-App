import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Component, ViewChild } from '@angular/core';
import { PostData } from '@interfaces/home/PostData';
import { PostService } from '@services/home/post.service';
import { UtilService } from '@services/utils/util.service';
import { of } from 'rxjs';
import { TextInputComponent } from '@home/shared-home/text-input/text-input.component';
import { HomePostsComponent } from "@home/home-posts/home-posts.component";
import { HttpClientTestingModule } from "@angular/common/http/testing";
import { SocialNavbarComponent } from "@home/shared-home/social-navbar/social-navbar.component";
import { IconsModule } from "@icons/icons.module";
import { SharedHomeModule } from "@home/shared-home/shared-home.module";
import { HomeComponent } from "@home/home.component";
import { HomeProfileComponent } from "@home/home-profile/home-profile.component";
import { HomeFriendsComponent } from "@home/home-friends/home-friends.component";
import { PostButtonBarComponent } from "@home/post-button-bar/post-button-bar.component";
import { CommonModule } from "@angular/common";
import { HomeRoutingModule } from "@home/home-routing.module";
import { MatListModule } from "@angular/material/list";
import { HomeDialogService } from "@services/home/home-dialog.service";

@Component({ selector: 'app-test', template: '' })
class TestComponent {
    @ViewChild(TextInputComponent) postTextInput!: TextInputComponent;
}

describe('HomePostsComponent', () => {
    let component: HomePostsComponent;
    let fixture: ComponentFixture<HomePostsComponent>;
    let postService: PostService;
    let utilService: UtilService;

    beforeEach(async () => {
        await TestBed.configureTestingModule({
            declarations: [
                HomeComponent,
                HomeProfileComponent,
                HomePostsComponent,
                HomeFriendsComponent,
                PostButtonBarComponent,
            ],
            imports: [
                CommonModule,
                HomeRoutingModule,
                SharedHomeModule,
                MatListModule,
                IconsModule,
                HttpClientTestingModule
            ],
            providers: [
                HomeDialogService
            ]
        }).compileComponents();
    });

    beforeEach(() => {
        fixture = TestBed.createComponent(HomePostsComponent);
        component = fixture.componentInstance;
        postService = TestBed.inject(PostService);
        utilService = TestBed.inject(UtilService);
        fixture.detectChanges();
    });

    afterEach(() => {
        fixture.destroy();
    });

    it('should create the component', () => {
        expect(component).toBeTruthy();
    });

    describe('loadNewPosts', () => {
        it('should load new posts and update the posts array', () => {
            const postData: PostData[] = [
                {
                    postContent: 'Post 1',
                    postAuthor: 'Author 1',
                    postDate: new Date(),
                    username: 'user1',
                    numOfLikes: 10,
                    numOfComments: 5,
                    postId: 1,
                    isPostLiked: false,
                    isUserInFriends: true
                },
                {
                    postContent: 'Post 2',
                    postAuthor: 'Author 2',
                    postDate: new Date(),
                    username: 'user2',
                    numOfLikes: 5,
                    numOfComments: 2,
                    postId: 2,
                    isPostLiked: true,
                    isUserInFriends: false
                }
            ];
            spyOn(postService, 'getListOfPosts').and.returnValue(of(postData));

            component.numOfSite = 0;
            component.loadNewPosts();

            expect(component.posts).toEqual(postData);
            expect(component.numOfSite).toBe(0);
            expect(component.isAllLoaded).toBe(true);
        });

        it('should set isAllLoaded to true when no more posts are available', () => {
            const postData: PostData[] = [
                {
                    postContent: 'Post 1',
                    postAuthor: 'Author 1',
                    postDate: new Date(),
                    username: 'user1',
                    numOfLikes: 10,
                    numOfComments: 5,
                    postId: 1,
                    isPostLiked: false,
                    isUserInFriends: true
                }
            ];
            spyOn(postService, 'getListOfPosts').and.returnValue(of(postData));

            component.numOfSite = 0;
            component.loadNewPosts();

            expect(component.posts).toEqual(postData);
            expect(component.numOfSite).toBe(0);
            expect(component.isAllLoaded).toBe(true);
        });
    });

    describe('createNewPost', () => {
        it('should create a new post and add it to the posts array', () => {
            const newPostData: PostData = {
                postContent: 'New post content',
                postAuthor: 'Author',
                postDate: new Date(),
                username: 'user',
                numOfLikes: 0,
                numOfComments: 0,
                postId: 3,
                isPostLiked: false,
                isUserInFriends: false
            };
            spyOn(postService, 'createNewPost').and.returnValue(of(newPostData));

            component.postTextInput.inputText = 'New post content';
            component.createNewPost();

            expect(component.posts[0]).toEqual(newPostData);
            expect(component.postTextInput.inputText).toBe('');
            expect(component.postTextInput.numOfCharacters).toBe(0);
        });
    });

    describe('deletePostFromList', () => {
        it('should delete the specified post from the posts array', () => {
            const postData1: PostData = {
                postContent: 'Post 1',
                postAuthor: 'Author 1',
                postDate: new Date(),
                username: 'user1',
                numOfLikes: 10,
                numOfComments: 5,
                postId: 1,
                isPostLiked: false,
                isUserInFriends: true
            };
            const postData2: PostData = {
                postContent: 'Post 2',
                postAuthor: 'Author 2',
                postDate: new Date(),
                username: 'user2',
                numOfLikes: 5,
                numOfComments: 2,
                postId: 2,
                isPostLiked: true,
                isUserInFriends: false
            };
            component.posts = [postData1, postData2];

            component.deletePostFromList(postData1);

            expect(component.posts).not.toContain(postData1);
            expect(component.posts).toContain(postData2);
        });
    });

    describe('updateFriendList', () => {
        it('should emit the updateFriendListEvent', () => {
            spyOn(component.updateFriendListEvent, 'emit');

            component.updateFriendList();

            expect(component.updateFriendListEvent.emit).toHaveBeenCalled();
        });
    });

    describe('ngOnInit', () => {
        it('should fetch the list of posts on initialization', () => {
            const postData: PostData[] = [
                {
                    postContent: 'Post 1',
                    postAuthor: 'Author 1',
                    postDate: new Date(),
                    username: 'user1',
                    numOfLikes: 10,
                    numOfComments: 5,
                    postId: 1,
                    isPostLiked: false,
                    isUserInFriends: true
                },
                {
                    postContent: 'Post 2',
                    postAuthor: 'Author 2',
                    postDate: new Date(),
                    username: 'user2',
                    numOfLikes: 5,
                    numOfComments: 2,
                    postId: 2,
                    isPostLiked: true,
                    isUserInFriends: false
                }
            ];
            spyOn(postService, 'getListOfPosts').and.returnValue(of(postData));
            spyOn(utilService, 'getValueFromStorage').and.returnValue('"username"');

            component.ngOnInit();

            expect(component.posts).toEqual(postData);
            expect(component.currentUser).toBe('username');
            expect(component.isAllLoaded).toBe(true);
        });

        it('should set isAllLoaded to true when no more posts are available', () => {
            const postData: PostData[] = [
                {
                    postContent: 'Post 1',
                    postAuthor: 'Author 1',
                    postDate: new Date(),
                    username: 'user1',
                    numOfLikes: 10,
                    numOfComments: 5,
                    postId: 1,
                    isPostLiked: false,
                    isUserInFriends: true
                }
            ];
            spyOn(postService, 'getListOfPosts').and.returnValue(of(postData));
            spyOn(utilService, 'getValueFromStorage').and.returnValue('"username"');

            component.ngOnInit();

            expect(component.posts).toEqual(postData);
            expect(component.currentUser).toBe('username');
            expect(component.isAllLoaded).toBe(true);
        });
    });

    describe('ngOnDestroy', () => {
        it('should complete the subjects', () => {
            spyOn(component.destroyPostList$, 'next');
            spyOn(component.destroyPostList$, 'complete');
            spyOn(component.destroyCreatePost$, 'next');
            spyOn(component.destroyCreatePost$, 'complete');
            spyOn(component.loadMorePosts$, 'next');
            spyOn(component.loadMorePosts$, 'complete');

            component.ngOnDestroy();

            expect(component.destroyPostList$.next).toHaveBeenCalled();
            expect(component.destroyPostList$.complete).toHaveBeenCalled();
            expect(component.destroyCreatePost$.next).toHaveBeenCalled();
            expect(component.destroyCreatePost$.complete).toHaveBeenCalled();
            expect(component.loadMorePosts$.next).toHaveBeenCalled();
            expect(component.loadMorePosts$.complete).toHaveBeenCalled();
        });
    });
});
