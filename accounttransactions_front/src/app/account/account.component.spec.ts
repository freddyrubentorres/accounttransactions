import { ComponentFixture, TestBed } from '@angular/core/testing';
import { AccountComponent } from './account.component';
import { ClientService } from '../client/service/client.service';
import { AccountService } from './service/account.service';
import { of, throwError } from 'rxjs';
import { NO_ERRORS_SCHEMA } from '@angular/core';

describe('AccountComponent', () => {
    let component: AccountComponent;
    let fixture: ComponentFixture<AccountComponent>;
    let mockClientService: jasmine.SpyObj<ClientService>;
    let mockAccountService: jasmine.SpyObj<AccountService>;

    beforeEach(() => {
        mockClientService = jasmine.createSpyObj('ClientService', ['getClientById']);
        mockAccountService = jasmine.createSpyObj('AccountService', ['createAccount']);

        TestBed.configureTestingModule({
            declarations: [AccountComponent],
            providers: [
                { provide: ClientService, useValue: mockClientService },
                { provide: AccountService, useValue: mockAccountService },
            ],
            schemas: [NO_ERRORS_SCHEMA]
        });

        fixture = TestBed.createComponent(AccountComponent);
        component = fixture.componentInstance;
    });

    it('should create the component', () => {
        expect(component).toBeTruthy();
    });

    describe('resetForm', () => {
        it('should reset the form data', () => {
            component.newAccount = {
                name: 'John Doe',
                id: '1234567890',
                accountType: 'AHORRO',
                initialBalance: '1000',
                client: { clientId: '1' }
            };
            component.resetForm();
            expect(component.newAccount.name).toBe('');
            expect(component.newAccount.initialBalance).toBe('');
        });
    });

    describe('validateIdentification', () => {
        it('should remove non-numeric characters and limit the length to 10 digits', () => {
            const event = { target: { value: '123ABC4567890' } };
            component.validateIdentification(event);
            expect(component.identification).toBe('1234567890');
        });
    });

    describe('validateAmount', () => {
        it('should remove non-numeric characters and limit to a valid number format', () => {
            const event = { target: { value: '123.45.67' } };
            component.validateAmount(event);
            expect(component.newAccount.initialBalance).toBe('1234567');
        });
    });

    describe('getClientData', () => {
        it('should display an error message if identification is empty', () => {
            component.identification = '';
            component.getClientData();
            expect(component.errorMessage).toBe('Por favor, ingrese un número de identificación');
          });
      
          it('should call getClientById and update clientData on success', () => {
            const mockResponse = {
              success: true,
              data: { name: 'John', last_name: 'Doe', identification: '1234567890', clientId: '1' }
            };
      
            mockClientService.getClientById.and.returnValue(of(mockResponse));
            
            component.identification = '1234567890';
            component.getClientData();
      
            expect(mockClientService.getClientById).toHaveBeenCalledWith('1234567890');
            expect(component.clientData).toEqual(mockResponse.data);
            expect(component.newAccount.name).toBe('John Doe');
            expect(component.showForm).toBeTrue();
          });
      
          it('should display an error message on failure', () => {
            const mockError = { error: { message: 'Client not found' } };
            
            mockClientService.getClientById.and.returnValue(throwError(mockError));
      
            component.identification = '1234567890';
            component.getClientData();
      
            expect(component.errorMessage).toBe('Client not found');
            expect(component.showForm).toBeFalse();
          });
    });
});
