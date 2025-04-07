import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../../../../src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class ClientService {
  private baseUrlgetClients: string = environment.baseUrlgetClients;
  private baseUrlpostClients = environment.baseUrlpostClients;
  constructor(private http: HttpClient) { }
  getClientById(identification: string): Observable<any> {
    const url = `${this.baseUrlgetClients}${identification}`;
    return this.http.get(url);
  }
  createClient(clientData: any): Observable<any> {
    const headers = new HttpHeaders({ 'Content-Type': 'application/json' });
    return this.http.post(this.baseUrlpostClients, clientData, { headers });
  }
}
