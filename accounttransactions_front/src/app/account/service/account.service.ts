import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../../../../src/environments/environment';
@Injectable({
  providedIn: 'root'
})
export class AccountService {
  private urlPostAccounts: string = environment.urlPostAccounts;
  constructor(private http: HttpClient) { }
  createAccount(accountData: any): Observable<any> {
    const headers = new HttpHeaders({ 'Content-Type': 'application/json' });
    return this.http.post(this.urlPostAccounts, accountData, { headers });
  }
}