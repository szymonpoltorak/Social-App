import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Component, Input } from '@angular/core';
import { FriendData } from '@interfaces/home/FriendData';
import { HomeFriendsComponent } from "@home/home-friends/home-friends.component";
import { MatDividerModule } from "@angular/material/divider";

@Component({ selector: 'app-test', template: '' })
class TestComponent {
    @Input() friendList: FriendData[] = [];
}

describe('HomeFriendsComponent', () => {
    let component: HomeFriendsComponent;
    let fixture: ComponentFixture<HomeFriendsComponent>;

    beforeEach(async () => {
        await TestBed.configureTestingModule({
            declarations: [HomeFriendsComponent, TestComponent],
            imports: [MatDividerModule]
        }).compileComponents();
    });

    beforeEach(() => {
        fixture = TestBed.createComponent(HomeFriendsComponent);
        component = fixture.componentInstance;
        fixture.detectChanges();
    });

    afterEach(() => {
        fixture.destroy();
    });

    it('should create the component', () => {
        expect(component).toBeTruthy();
    });

    describe('removeFriendFromList', () => {
        it('should remove the friend from the friendList', () => {
            component.friendList = [
                { friendFullName: 'John Doe', friendJob: 'Developer', friendUsername: 'john123' },
                { friendFullName: 'Jane Smith', friendJob: 'Designer', friendUsername: 'jane456' },
                { friendFullName: 'Bob Johnson', friendJob: 'Engineer', friendUsername: 'bob789' }
            ];
            component.removeFriendFromList('jane456');

            expect(component.friendList.length).toBe(2);
            expect(component.friendList).not.toContain(jasmine.objectContaining({ friendUsername: 'jane456' }));
        });

        it('should not remove any friend if friendUsername does not match', () => {
            const friendList: FriendData[] = [
                { friendFullName: 'John Doe', friendJob: 'Developer', friendUsername: 'john123' },
                { friendFullName: 'Jane Smith', friendJob: 'Designer', friendUsername: 'jane456' }
            ];

            component.friendList = friendList;
            component.removeFriendFromList('bob789');

            expect(component.friendList.length).toBe(2);
            expect(component.friendList).toEqual(friendList);
        });
    });
});
