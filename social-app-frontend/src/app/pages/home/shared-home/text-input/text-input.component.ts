import { Component, Input, OnInit } from '@angular/core';
import { TextInputInterface } from "@interfaces/home/TextInputInterface";

@Component({
    selector: 'app-text-input',
    templateUrl: './text-input.component.html',
    styleUrls: ['./text-input.component.scss']
})
export class TextInputComponent implements TextInputInterface, OnInit {
    @Input() windowWidth: string = "";
    inputText: string = "";
    numOfCharacters: number = 0;

    updateCharacterCounter(): void {
        this.numOfCharacters = this.inputText.length;
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

    ngOnInit(): void {
    }
}
