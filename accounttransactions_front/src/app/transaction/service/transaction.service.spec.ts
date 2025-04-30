import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { TransactionService } from './transaction.service';
import { environment } from '../../../../src/environments/environment';
import { HttpHeaders } from '@angular/common/http';
describe('TransactionService', () => {
    let service: TransactionService;
    let httpMock: HttpTestingController;
    beforeEach(() => {
        TestBed.configureTestingModule({
            imports: [HttpClientTestingModule],
            providers: [TransactionService]
        });

        service = TestBed.inject(TransactionService);
        httpMock = TestBed.inject(HttpTestingController);
    });
    afterEach(() => {
        httpMock.verify();
    });
    it('should be created', () => {
        expect(service).toBeTruthy();
    });
    it('should call getAccountNumber with correct URL', () => {
        const accountNumber = '123456789';
        service.getAccountNumber(accountNumber).subscribe();

        const req = httpMock.expectOne(`${environment.urlGetAccounts}${accountNumber}`);
        expect(req.request.method).toBe('GET');
        req.flush({});
    });
    it('should call createTransaction with correct data and headers', () => {
        const mockTransaction = {
            accountId: '123',
            amount: 100.00,
            transactionType: 'DEPOSIT',
            description: 'Test transaction'
        };
        service.createTransaction(mockTransaction).subscribe();
        const req = httpMock.expectOne(environment.urlPostTransactions);
        expect(req.request.method).toBe('POST');
        expect(req.request.headers.get('Content-Type')).toBe('application/json');
        expect(req.request.body).toEqual(mockTransaction);
        req.flush({});
    });
});
