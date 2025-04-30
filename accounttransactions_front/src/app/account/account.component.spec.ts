import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { AccountComponent } from './account.component';
import { ClientService } from '../client/service/client.service';
import { AccountService } from './service/account.service';
import { of, throwError } from 'rxjs';
import { FormsModule } from '@angular/forms';

describe('AccountComponent', () => {
  let component: AccountComponent;
  let fixture: ComponentFixture<AccountComponent>;
  let mockClientService: jasmine.SpyObj<ClientService>;
  let mockAccountService: jasmine.SpyObj<AccountService>;
  beforeEach(async () => {
    mockClientService = jasmine.createSpyObj('ClientService', ['getClientById']);
    mockAccountService = jasmine.createSpyObj('AccountService', ['createAccount']);
    await TestBed.configureTestingModule({
      declarations: [AccountComponent],
      imports: [FormsModule],
      providers: [
        { provide: ClientService, useValue: mockClientService },
        { provide: AccountService, useValue: mockAccountService }
      ]
    }).compileComponents();
    fixture = TestBed.createComponent(AccountComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });
  it('should create the component', () => {
    expect(component).toBeTruthy();
  });
  it('should show error when identification is empty', () => {
    component.identification = '';
    component.getClientData();
    expect(component.errorMessage).toBe('Ingrese un número de identificación');
  });
  it('should fetch client data and populate form', fakeAsync(() => {
    const mockResponse = {
      success: true,
      data: {
        name: 'Juan',
        lastName: 'Perez',
        identification: '1234567890'
      }
    };
    mockClientService.getClientById.and.returnValue(of(mockResponse));
    component.identification = '1234567890';
    component.getClientData();
    tick();
    expect(component.showForm).toBeTrue();
    expect(component.clientData).toEqual(mockResponse.data);
    expect(component.newAccount.name).toBe('Juan Perez');
    expect(component.newAccount.id).toBe('1234567890');
  }));
  it('should handle client fetch error', fakeAsync(() => {
    const errorResponse = {
      error: { message: 'Cliente no encontrado' }
    };
    mockClientService.getClientById.and.returnValue(throwError(() => errorResponse));
    component.identification = '1234567890';
    component.getClientData();
    tick();
    expect(component.showForm).toBeFalse();
    expect(component.errorMessage).toBe('Cliente no encontrado');
    expect(component.clientData).toBeNull();
  }));
  it('should submit account and reset form', fakeAsync(() => {
    component.newAccount.name = 'Juan Perez';
    component.newAccount.initialBalance = '1000';
    mockAccountService.createAccount.and.returnValue(of({ message: 'Cuenta creada' }));
    component.onSubmit();
    tick();
    expect(component.errorMessage).toBe('Cuenta creada');
    expect(component.newAccount.name).toBe('');
  }));
  it('should show error when submitting incomplete form', () => {
    component.newAccount.name = '';
    component.newAccount.initialBalance = '';
    component.onSubmit();
    expect(component.errorMessage).toBe('Nombre es obligatoria');
  });
});
