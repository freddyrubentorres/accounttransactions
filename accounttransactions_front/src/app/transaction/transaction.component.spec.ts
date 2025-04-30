import { ComponentFixture, TestBed } from '@angular/core/testing';
import { TransactionComponent } from './transaction.component';
import { TransactionService } from './service/transaction.service';
import { of, throwError } from 'rxjs';

describe('TransactionComponent', () => {
    let component: TransactionComponent;
    let fixture: ComponentFixture<TransactionComponent>;
    let mockTransactionService: jasmine.SpyObj<TransactionService>;

    beforeEach(() => {
        mockTransactionService = jasmine.createSpyObj('TransactionService', ['getAccountNumber', 'createTransaction']);

        TestBed.configureTestingModule({
            declarations: [TransactionComponent],
            providers: [
                { provide: TransactionService, useValue: mockTransactionService }
            ]
        });

        fixture = TestBed.createComponent(TransactionComponent);
        component = fixture.componentInstance;
    });

    it('should create the component', () => {
        expect(component).toBeTruthy();
    });

    describe('getClientData', () => {
        it('should display an error message if accountNumber is empty', () => {
            component.accountNumber = '';
            component.getClientData();
            expect(component.errorMessage).toBe('Ingrese numero de cuenta');
        });

        it('should fetch account data and display the form if account exists', () => {
            const mockResponse = { success: true, data: { accountNumber: '123456', accountType: 'Saving' } };
            mockTransactionService.getAccountNumber.and.returnValue(of(mockResponse));

            component.accountNumber = '123456';
            component.getClientData();

            expect(mockTransactionService.getAccountNumber).toHaveBeenCalledWith('123456');
            expect(component.showForm).toBeTrue();
            expect(component.accountData).toEqual(mockResponse.data);
            expect(component.newTransaction.account.accountNumber).toBe('123456');
            expect(component.newTransaction.typeAccount).toBe('Saving');
        });

        it('should handle error if getAccountNumber fails', () => {
            const mockError = { error: { message: 'Account not found' } };
            mockTransactionService.getAccountNumber.and.returnValue(throwError(mockError));

            component.accountNumber = '123456';
            component.getClientData();

            expect(component.errorMessage).toBe('Account not found');
            expect(component.showForm).toBeFalse();
        });
    });
    describe('validateAccountNumber', () => {
        it('should allow only numeric characters and limit the length to 10', () => {
            const event = { target: { value: '12345678901234' } };
            component.validateAccountNumber(event as any);
            expect(component.accountNumber).toBe('1234567890');
        });

        it('should not allow more than 10 digits', () => {
            const event = { target: { value: '1234567890' } };
            component.validateAccountNumber(event as any);
            expect(component.accountNumber).toBe('1234567890');
        });
    });
});
