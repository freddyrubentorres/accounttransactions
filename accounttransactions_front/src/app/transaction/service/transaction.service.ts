import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../../../../src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class TransactionService {
  private baseUrlgetClients: string = environment.baseUrlgetAccount;
  private baseUrlpostTransaction: string = environment.baseUrlpostTransaction;
  constructor(private http: HttpClient) { }
  getAccountNumber(accountNumber: string): Observable<any> {
    const url = `${this.baseUrlgetClients}${accountNumber}`;
    return this.http.get(url);
  }
  createTransaction(transactionData: any): Observable<any> {
    const headers = new HttpHeaders({ 'Content-Type': 'application/json' });
    return this.http.post(this.baseUrlpostTransaction, transactionData, { headers });
  }
}
