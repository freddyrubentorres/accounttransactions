import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { ReportService } from './service/report.service';
import { jsPDF } from 'jspdf';
import html2canvas from 'html2canvas';

@Component({
  selector: 'app-report',
  standalone: false,
  templateUrl: './report.component.html',
  styleUrl: './report.component.css'
})
export class ReportComponent {
  identification: string = '';
  startDate: Date = new Date();
  endDate: Date = new Date();
  data: any[] = [];
  displayedColumns: string[] = ['accountId', 'movement'];
  errorMessage: string = '';

  constructor(private http: HttpClient, private reportService: ReportService) { }

  // GETDATA
  getClientData(): void {
    this.data = [];
    if (!this.identification.trim()) {
      this.errorMessage = 'Ingrese un número de identificación';
      return;
    }
    if (!this.startDate || isNaN(this.startDate.getTime())) {
      this.errorMessage = 'Ingrese fecha de inicio';
      return;
    }
    if (!this.endDate || isNaN(this.endDate.getTime())) {
      this.errorMessage = 'Ingrese fecha de inicio';
      return;
    }
    this.errorMessage = '';
    const startDateString = this.startDate.toISOString().split('T')[0];
    const endDateString = this.endDate.toISOString().split('T')[0];
    this.reportService.getReport(this.identification, startDateString, endDateString).subscribe(
      (data) => {
        this.transformData(data);
      },
      (error) => {
        console.log(error)
        if (error.error.message) {
          this.errorMessage = error.error.message;
        } else {
          this.errorMessage = error;
        }
      }
    );
  }
  transformData(response: any) {
    this.data = Object.keys(response.data).map(key => {
      return {
        accountId: key,
        movements: response.data[key]
      };
    });
  }
  //VALIDACIONES
  validateIdentification(event: any): void {
    const input = event.target as HTMLInputElement;
    let inputValue = input.value.replace(/[^0-9]/g, '');
    if (inputValue.length > 10) {
      inputValue = inputValue.slice(0, 10);
    }
    input.value = inputValue;
    this.identification = inputValue;
  }
  //PDF
  exportToPDF() {
    const doc = new jsPDF();
    const content = document.querySelector('table');
    if (!content) return;
    html2canvas(content).then((canvas) => {
      const imgData = canvas.toDataURL('image/png');
      const imgProps = {
        width: canvas.width,
        height: canvas.height,
      };
      const pdfWidth = doc.internal.pageSize.getWidth();
      const pdfHeight = doc.internal.pageSize.getHeight();
      const scale = Math.min(pdfWidth / imgProps.width, pdfHeight / imgProps.height);
      const imgWidth = imgProps.width * scale;
      const imgHeight = imgProps.height * scale;
      const x = (pdfWidth - imgWidth) / 2;
      const y = 10;
      doc.addImage(imgData, 'PNG', x, y, imgWidth, imgHeight);
      doc.save('reporte.pdf');
    });
  }
}

