import { Component, EventEmitter, Inject, OnInit, Output } from '@angular/core';
import { MAT_DIALOG_DATA } from "@angular/material/dialog";
import { FormBuilder, FormGroup, Validators } from "@angular/forms";
import { LinkValidation } from "@core/enums/LinkValidation";

@Component({
    selector: 'app-edit-dialog',
    templateUrl: './edit-dialog.component.html',
    styleUrls: ['./edit-dialog.component.scss']
})
export class EditDialogComponent implements OnInit {
    @Output() closeEvent: EventEmitter<any> = new EventEmitter();
    title: string;
    url: string;
    editInputGroup !: FormGroup;

    constructor(@Inject(MAT_DIALOG_DATA) data: any,
                private formBuilder: FormBuilder) {
        // constructor(@Inject(MAT_DIALOG_DATA) data: any,
        // private userDataService: UserHomeDataService,
        // private formBuilder: FormBuilder) {
        this.title = data.title;
        this.url = data.url;
    }

    exit(): void {
        this.closeEvent.emit();
    }

    submitValue(): void {
        if (this.editInputGroup.invalid) {
            return;
        }
    }

    ngOnInit(): void {
        this.editInputGroup = this.formBuilder.group({
            linkInput: [LinkValidation.LINK_VALUE,
                [
                    Validators.pattern(LinkValidation.LINK_REGEX),
                    Validators.required
                ]
            ]
        });
    }
}
