import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { ClientComponent } from './client.component';
import { ClientService } from './service/client.service';
import { FormsModule } from '@angular/forms';
import { of, throwError } from 'rxjs';

describe('ClientComponent', () => {
  let component: ClientComponent;
  let fixture: ComponentFixture<ClientComponent>;
  let mockClientService: jasmine.SpyObj<ClientService>;

  beforeEach(async () => {
    mockClientService = jasmine.createSpyObj('ClientService', ['getClientById', 'createClient']);

    await TestBed.configureTestingModule({
      declarations: [ClientComponent],
      imports: [FormsModule],
      providers: [
        { provide: ClientService, useValue: mockClientService }
      ]
    }).compileComponents();

    fixture = TestBed.createComponent(ClientComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create component', () => {
    expect(component).toBeTruthy();
  });

  it('should show error if identification is empty', () => {
    component.identification = '';
    component.getClientData();
    expect(component.errorMessage).toBe('Ingrese un número de identificación');
  });

  it('should fetch client data successfully', fakeAsync(() => {
    const mockResponse = {
      success: true,
      data: { name: 'Juan', identification: '123' }
    };
    mockClientService.getClientById.and.returnValue(of(mockResponse));
    component.identification = '123';

    component.getClientData();
    tick();

    expect(component.clientData).toEqual(mockResponse.data);
    expect(component.isLoading).toBeFalse();
    expect(component.errorMessage).toBe('');
  }));

  it('should handle error on getClientData', fakeAsync(() => {
    mockClientService.getClientById.and.returnValue(
      throwError(() => ({ error: { message: 'Cliente no encontrado' } }))
    );

    component.identification = '123';
    component.getClientData();
    tick();

    expect(component.errorMessage).toBe('Cliente no encontrado');
    expect(component.clientData).toBeNull();
    expect(component.isLoading).toBeFalse();
  }));

  it('should validate email correctly', () => {
    const input = { target: { value: 'invalid-email' } } as any;
    component.validateEmail(input);
    expect(component.emailInvalid).toBeTrue();

    const validInput = { target: { value: 'test@example.com' } } as any;
    component.validateEmail(validInput);
    expect(component.emailInvalid).toBeFalse();
  });

  it('should validate phone number', () => {
    const event = { target: { value: '12345abc67890' } } as any;
    component.validatePhone(event);
    expect(component.newEmployee.phone).toBe('1234567890');
  });

  it('should validate identification input', () => {
    const event = { target: { value: 'abc1234567890' } } as any;
    component.validateIdentification(event);
    expect(component.newEmployee.identification).toBe('1234567890');
  });

  it('should validate age input', () => {
    const event = { target: { value: 'abc1234' } } as any;
    component.validateAge(event);
    expect(component.newEmployee.age).toBe('12');
  });
});