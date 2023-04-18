import { Component, OnInit } from "@angular/core";
import { FormGroup } from "@angular/forms";
import { RegisterControlProviderService } from "../services/register-control-provider.service";
import { FormFieldNames } from "../../../core/enums/FormFieldNames";
import { MatDialog } from "@angular/material/dialog";
import { DialogContents } from "../../../core/enums/DialogContents";
import { DialogService } from "../services/dialog.service";
import { FormBuildingService } from "../services/form-building.service";

@Component({
    selector: "app-register",
    templateUrl: "./register.component.html",
    styleUrls: ["./register.component.scss"]
})
export class RegisterComponent implements OnInit {
    registerForm!: FormGroup;
    wasSubmitClicked: boolean = false;
    passwordMismatch!: boolean;
    private dialogListItems !: Array<string>;
    private paragraphContent !: string;

    constructor(public controlProvider: RegisterControlProviderService,
                private formBuildingService: FormBuildingService,
                private notFilled: MatDialog,
                private dialogService: DialogService) {
    }

    makeRedirection(): void {
        if (this.registerForm.invalid) {
            this.wasSubmitClicked = true;

            this.dialogService.openNotFilledDialog(this.notFilled, this.paragraphContent, this.dialogListItems);

            return;
        }
    }

    ngOnInit(): void {
        this.registerForm = this.formBuildingService.buildRegisterForm();

        this.registerForm.get(FormFieldNames.PASSWORD_GROUP)?.valueChanges.subscribe(() => {
            this.passwordMismatch = <boolean>this.registerForm.get(FormFieldNames.PASSWORD_GROUP)?.invalid;
        });

        this.dialogListItems = [DialogContents.REGISTER_REQUIRED, DialogContents.REGISTER_YEAR,
            DialogContents.REGISTER_PASSWORD, DialogContents.REGISTER_SAME_PASSWORDS];
        this.paragraphContent = DialogContents.REGISTER_PARAGRAPH;
    }
}
