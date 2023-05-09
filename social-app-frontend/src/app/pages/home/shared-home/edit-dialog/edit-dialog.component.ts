import { Component, EventEmitter, Inject, OnDestroy, OnInit, Output } from '@angular/core';
import { MAT_DIALOG_DATA } from "@angular/material/dialog";
import { FormBuilder, FormGroup, Validators } from "@angular/forms";
import { LinkValidation } from "@core/enums/LinkValidation";
import { UserHomeDataService } from "@core/services/user-home-data.service";
import { UtilService } from "@core/services/util.service";
import { StorageKeys } from "@core/enums/StorageKeys";
import { Subject, takeUntil } from "rxjs";

@Component({
    selector: 'app-edit-dialog',
    templateUrl: './edit-dialog.component.html',
    styleUrls: ['./edit-dialog.component.scss']
})
export class EditDialogComponent implements OnInit, OnDestroy {
    private destroyData$: Subject<void> = new Subject<void>();
    @Output() closeEvent: EventEmitter<any> = new EventEmitter();
    title: string;
    url: string;
    editInputGroup !: FormGroup;

    constructor(@Inject(MAT_DIALOG_DATA) data: any,
                private userDataService: UserHomeDataService,
                private formBuilder: FormBuilder,
                private utilService: UtilService) {
        this.title = data.title;
        this.url = data.url;
    }

    exit(): void {
        this.closeEvent.emit(null);
    }

    submitValue(): void {
        if (this.editInputGroup.invalid) {
            return;
        }
        let username: string = this.utilService.getValueFromStorage(StorageKeys.USERNAME);
        const userData: string = this.editInputGroup.get('linkInput')?.value;

        username = username.substring(1, username.length - 1);

        console.log(`Username : ${ username }`);
        console.log(`UserData : ${ userData }`);
        console.log(`Url : ${ this.url }`);

        this.userDataService.updateUsersData(username, userData, this.url)
            .pipe(takeUntil(this.destroyData$))
            .subscribe((): void => {
                this.closeEvent.emit(userData);
            });
    }

    ngOnInit(): void {
        this.editInputGroup = this.formBuilder.group({
            linkInput: [LinkValidation.LINK_VALUE,
                [
                    Validators.required
                ]
            ]
        });
    }

    ngOnDestroy(): void {
        this.destroyData$.next();
        this.destroyData$.complete();
    }
}
