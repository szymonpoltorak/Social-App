import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Component } from '@angular/core';
import { PostButtonBarComponent } from "@home/post-button-bar/post-button-bar.component";

@Component({ selector: 'app-test', template: '' })
class TestComponent {}

describe('PostButtonBarComponent', () => {
    let component: PostButtonBarComponent;
    let fixture: ComponentFixture<PostButtonBarComponent>;

    beforeEach(async () => {
        await TestBed.configureTestingModule({
            declarations: [PostButtonBarComponent, TestComponent]
        }).compileComponents();
    });

    beforeEach(() => {
        fixture = TestBed.createComponent(PostButtonBarComponent);
        component = fixture.componentInstance;
        fixture.detectChanges();
    });

    afterEach(() => {
        fixture.destroy();
    });

    it('should create the component', () => {
        expect(component).toBeTruthy();
    });

    it('should emit the createEvent when emitCreatePostEvent is called', () => {
        spyOn(component.createEvent, 'emit');
        component.emitCreatePostEvent();
        expect(component.createEvent.emit).toHaveBeenCalled();
    });
});
