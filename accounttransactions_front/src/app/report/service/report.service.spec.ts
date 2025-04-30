import { TestBed } from '@angular/core/testing';
import { provideHttpClient } from '@angular/common/http';
import { provideHttpClientTesting, HttpTestingController } from '@angular/common/http/testing';
import { ReportService } from './report.service';
import { environment } from '../../../../src/environments/environment';

describe('ReportService', () => {
  let service: ReportService;
  let httpMock: HttpTestingController;
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [
        ReportService,
        provideHttpClient(),
        provideHttpClientTesting()
      ]
    });
    service = TestBed.inject(ReportService);
    httpMock = TestBed.inject(HttpTestingController);
  });
  afterEach(() => {
    httpMock.verify();
  });
  it('should be created', () => {
    expect(service).toBeTruthy();
  });
  it('should call getReport with correct URL and parameters', () => {
    const identification = '12345';
    const startDate = '2024-01-01';
    const endDate = '2024-01-31';
    service.getReport(identification, startDate, endDate).subscribe();
    const req = httpMock.expectOne(
      `${environment.urlGetReports}?identification=${identification}&startDate=${startDate}&endDate=${endDate}`
    );
    expect(req.request.method).toBe('GET');
    req.flush({});
  });
});
