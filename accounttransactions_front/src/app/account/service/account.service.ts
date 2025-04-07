import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../../../../src/environments/environment';
@Injectable({
  providedIn: 'root'
})
export class AccountService {
  private baseUrlpostAccount: string = environment.baseUrlpostAccount;
  constructor(private http: HttpClient) { }
  createAccount(accountData: any): Observable<any> {
    const headers = new HttpHeaders({ 'Content-Type': 'application/json' });
    return this.http.post(this.baseUrlpostAccount, accountData, { headers });
  }
}