import { Component } from '@angular/core';

@Component({
    selector: 'app-text-input',
    templateUrl: './text-input.component.html',
    styleUrls: ['./text-input.component.scss']
})
export class TextInputComponent {
    postText!: string;
    numOfCharacters: number = 0;

    updateCharacterCounter(): void {
        this.numOfCharacters = this.postText.length;
    }
}
