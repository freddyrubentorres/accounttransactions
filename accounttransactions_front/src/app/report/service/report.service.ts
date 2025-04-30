import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from '../../../../src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class ReportService {
  private urlGetReports: string = environment.urlGetReports
  constructor(private http: HttpClient) { }
  getReport(identification: string, startDate: string, endDate: string) {
    const url = `${environment.urlGetReports}?identification=${identification}&startDate=${startDate}&endDate=${endDate}`;
    return this.http.get(url);
  }
}
