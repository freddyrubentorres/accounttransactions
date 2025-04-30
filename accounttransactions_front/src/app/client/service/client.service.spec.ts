import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { ClientService } from './client.service';
import { environment } from '../../../../src/environments/environment';

describe('ClientService', () => {
  let service: ClientService;
  let httpMock: HttpTestingController;

  const mockClientData = {
    name: 'Ana',
    lastName: 'López',
    identification: '1234567890',
  };

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      providers: [ClientService],
    });

    service = TestBed.inject(ClientService);
    httpMock = TestBed.inject(HttpTestingController);
  });

  afterEach(() => {
    httpMock.verify(); // Verifica que no haya peticiones pendientes
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });

  it('should call getClientById and return client data', () => {
    const id = '1234567890';

    service.getClientById(id).subscribe((res) => {
      expect(res).toEqual(mockClientData);
    });

    const req = httpMock.expectOne(`${environment.urlGetClients}${id}`);
    expect(req.request.method).toBe('GET');
    req.flush(mockClientData);
  });

  it('should call createClient and return success message', () => {
    const payload = mockClientData;
    const response = { message: 'Cliente creado exitosamente' };

    service.createClient(payload).subscribe((res) => {
      expect(res).toEqual(response);
    });

    const req = httpMock.expectOne(environment.urlPostClients);
    expect(req.request.method).toBe('POST');
    expect(req.request.body).toEqual(payload);
    expect(req.request.headers.get('Content-Type')).toBe('application/json');
    req.flush(response);
  });
});

