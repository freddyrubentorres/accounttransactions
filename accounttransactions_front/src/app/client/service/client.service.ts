import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../../../../src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class ClientService {
  private urlPostClients: string = environment.urlPostClients;
  private urlGetClients = environment.urlGetClients;
  constructor(private http: HttpClient) { }
  getClientById(identification: string): Observable<any> {
    const url = `${this.urlGetClients}${identification}`;
    return this.http.get(url);
  }
  createClient(clientData: any): Observable<any> {
    const headers = new HttpHeaders({ 'Content-Type': 'application/json' });
    return this.http.post(this.urlPostClients, clientData, { headers });
  }
}
