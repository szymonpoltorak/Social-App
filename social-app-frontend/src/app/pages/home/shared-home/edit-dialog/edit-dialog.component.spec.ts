import { ComponentFixture, TestBed } from '@angular/core/testing';
import { FormBuilder, FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MAT_DIALOG_DATA, MatDialogModule } from '@angular/material/dialog';
import { By } from '@angular/platform-browser';
import { of, Subject } from 'rxjs';
import { EditDialogComponent } from './edit-dialog.component';
import { UserHomeDataService } from '@core/services/home/user-home-data.service';
import { LinkValidation } from '@core/enums/LinkValidation';

describe('EditDialogComponent', () => {
    let component: EditDialogComponent;
    let fixture: ComponentFixture<EditDialogComponent>;
    let userDataService: UserHomeDataService;
    let closeEventSpy: jasmine.Spy;

    beforeEach(async () => {
        const userDataServiceMock = {
            updateUsersData: jasmine.createSpy('updateUsersData').and.returnValue(of(null)),
        };

        await TestBed.configureTestingModule({
            declarations: [EditDialogComponent],
            imports: [FormsModule, ReactiveFormsModule, MatDialogModule],
            providers: [
                FormBuilder,
                { provide: MAT_DIALOG_DATA, useValue: { title: 'Test Title', url: 'test-url' } },
                { provide: UserHomeDataService, useValue: userDataServiceMock },
            ],
        }).compileComponents();
    });

    beforeEach(() => {
        fixture = TestBed.createComponent(EditDialogComponent);
        component = fixture.componentInstance;
        userDataService = TestBed.inject(UserHomeDataService);
        closeEventSpy = spyOn(component.closeEvent, 'emit');
        fixture.detectChanges();
    });

    afterEach(() => {
        fixture.destroy();
    });

    it('should create the component', () => {
        expect(component).toBeTruthy();
    });

    it('should initialize form group with default values', () => {
        expect(component.editInputGroup.get('linkInput')?.value).toBe(LinkValidation.LINK_VALUE);
    });

    it('should emit close event when exit method is called', () => {
        component.exit();

        expect(closeEventSpy).toHaveBeenCalled();
    });

    it('should not call updateUsersData method if the form is invalid', () => {
        const updateUsersDataSpy = userDataService.updateUsersData as jasmine.Spy;
        const formElement = fixture.debugElement.query(By.css('form')).nativeElement;

        formElement.dispatchEvent(new Event('submit'));
        fixture.detectChanges();

        expect(updateUsersDataSpy).not.toHaveBeenCalled();
    });

    it('should call updateUsersData method with the correct parameters when the form is valid', () => {
        const updateUsersDataSpy = userDataService.updateUsersData as jasmine.Spy;
        const formElement = fixture.debugElement.query(By.css('form')).nativeElement;
        const linkInput = component.editInputGroup.get('linkInput');

        linkInput?.setValue('new-value');
        formElement.dispatchEvent(new Event('submit'));
        fixture.detectChanges();

        expect(updateUsersDataSpy).toHaveBeenCalledWith('new-value', 'test-url');
    });

    it('should emit close event with the updated user data when the form is valid', () => {
        const formElement = fixture.debugElement.query(By.css('form')).nativeElement;
        const linkInput = component.editInputGroup.get('linkInput');

        linkInput?.setValue('new-value');
        formElement.dispatchEvent(new Event('submit'));
        fixture.detectChanges();

        expect(closeEventSpy).toHaveBeenCalledWith('new-value');
    });

    it('should complete the destroyData$ subject when component is destroyed', () => {
        const destroyDataSubject: Subject<void> = (component as any).destroyData$;
        spyOn(destroyDataSubject, 'next');
        spyOn(destroyDataSubject, 'complete');

        component.ngOnDestroy();

        expect(destroyDataSubject.next).toHaveBeenCalled();
        expect(destroyDataSubject.complete).toHaveBeenCalled();
    });
});
