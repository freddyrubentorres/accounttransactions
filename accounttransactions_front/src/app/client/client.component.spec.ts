import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ClientComponent } from './client.component';
import { ClientService } from './service/client.service';
import { of, throwError } from 'rxjs';

describe('ClientComponent', () => {
    let component: ClientComponent;
    let fixture: ComponentFixture<ClientComponent>;
    let mockClientService: jasmine.SpyObj<ClientService>;

    beforeEach(() => {
        mockClientService = jasmine.createSpyObj('ClientService', ['getClientById', 'createClient']);

        TestBed.configureTestingModule({
            declarations: [ClientComponent],
            providers: [
                { provide: ClientService, useValue: mockClientService }
            ]
        });

        fixture = TestBed.createComponent(ClientComponent);
        component = fixture.componentInstance;
    });

    it('should create the component', () => {
        expect(component).toBeTruthy();
    });

    describe('getClientData', () => {
        it('should display an error message if identification is empty', () => {
            component.identification = '';
            component.getClientData();
            expect(component.errorMessage).toBe('Por favor, ingrese un número de identificación');
        });

        it('should fetch and update client data if identification is valid', () => {
            const mockResponse = {
                success: true,
                data: { name: 'John', last_name: 'Doe', identification: '1234567890' }
            };

            mockClientService.getClientById.and.returnValue(of(mockResponse));
            component.identification = '1234567890';
            component.getClientData();

            expect(mockClientService.getClientById).toHaveBeenCalledWith('1234567890');
            expect(component.clientData).toEqual(mockResponse.data);
            expect(component.identification).toBe('');
            expect(component.errorMessage).toBe('');
        });

        it('should handle error if getClientById fails', () => {
            const mockError = { error: { message: 'Client not found' } };
            mockClientService.getClientById.and.returnValue(throwError(mockError));

            component.identification = '1234567890';
            component.getClientData();

            expect(component.errorMessage).toBe('Client not found');
            expect(component.clientData).toBeNull();
        });
    });
    describe('resetForm', () => {
        it('should reset the form values', () => {
            component.newEmployee = {
                email: 'test@example.com',
                age: '30',
                password: 'password123',
                name: 'John',
                last_name: 'Doe',
                gender: 'M',
                identification: '1234567890',
                address: '123 Main St',
                phone: '1234567890'
            };
            component.resetForm();
            expect(component.newEmployee.email).toBe('');
            expect(component.newEmployee.age).toBe('');
            expect(component.newEmployee.password).toBe('');
            expect(component.newEmployee.name).toBe('');
            expect(component.newEmployee.last_name).toBe('');
            expect(component.newEmployee.phone).toBe('');
        });
    });
    describe('validateIdentification', () => {
        it('should allow only numbers and limit input to 10 digits', () => {
            const event = { target: { value: '12345678901234' } };
            component.validateIdentification(event as any);
            expect(component.newEmployee.identification).toBe('1234567890');
        });
    });

    describe('validateAge', () => {
        it('should allow only numbers and limit input to 2 digits', () => {
            const event = { target: { value: '123' } };
            component.validateAge(event as any);
            expect(component.newEmployee.age).toBe('12');
        });
    });

    describe('validateEmail', () => {
        it('should set emailInvalid to true for invalid email format', () => {
            const event = { target: { value: 'invalid-email' } };
            component.validateEmail(event as any);
            expect(component.emailInvalid).toBeTrue();
        });

        it('should set emailInvalid to false for valid email format', () => {
            const event = { target: { value: 'valid@example.com' } };
            component.validateEmail(event as any);
            expect(component.emailInvalid).toBeFalse();
        });
    });

});
