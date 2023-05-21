import { Component, Input } from '@angular/core';

@Component({
    selector: 'app-text-input',
    templateUrl: './text-input.component.html',
    styleUrls: ['./text-input.component.scss']
})
export class TextInputComponent {
    @Input() windowWidth !: string;
    postText!: string;
    numOfCharacters: number = 0;

    updateCharacterCounter(): void {
        this.numOfCharacters = this.postText.length;
    }

    getInputSize(): string {
        if (this.numOfCharacters < 45) {
            return '2em';
        }
        if (this.numOfCharacters < 110) {
            return '4em';
        }
        return '4.5em';
    }
}
