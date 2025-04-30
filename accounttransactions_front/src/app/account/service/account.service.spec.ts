import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { AccountService } from './account.service';
import { environment } from '../../../../src/environments/environment';
describe('AccountService', () => {
    let service: AccountService;
    let httpMock: HttpTestingController;
    const mockAccountData = {
        accountNumber: '1234567890',
        type: 'AHORRO',
        balance: 5000
    };
    beforeEach(() => {
        TestBed.configureTestingModule({
            imports: [HttpClientTestingModule],
            providers: [AccountService]
        });
        service = TestBed.inject(AccountService);
        httpMock = TestBed.inject(HttpTestingController);
    });
    afterEach(() => {
        httpMock.verify();
    });
    it('should be created', () => {
        expect(service).toBeTruthy();
    });
    it('should send POST request to create account', () => {
        service.createAccount(mockAccountData).subscribe((response) => {
            expect(response).toBeTruthy();
        });
        const req = httpMock.expectOne(environment.urlPostAccounts);
        expect(req.request.method).toBe('POST');
        expect(req.request.body).toEqual(mockAccountData);
        expect(req.request.headers.get('Content-Type')).toBe('application/json');
        req.flush({ success: true });
    });
});
