import { Component, EventEmitter, Output } from '@angular/core';

@Component({
    selector: 'app-post-button-bar',
    templateUrl: './post-button-bar.component.html',
    styleUrls: ['./post-button-bar.component.scss']
})
export class PostButtonBarComponent {
    @Output() createEvent: EventEmitter<void> = new EventEmitter<void>();

    emitCreatePostEvent(): void {
        this.createEvent.emit();
    }
}
