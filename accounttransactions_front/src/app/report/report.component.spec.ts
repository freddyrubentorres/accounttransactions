import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ReportComponent } from './report.component';
import { ReportService } from './service/report.service';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { of, throwError } from 'rxjs';
import { FormsModule } from '@angular/forms';
import { CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatNativeDateModule } from '@angular/material/core';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatTableModule } from '@angular/material/table';

describe('ReportComponent', () => {
    let component: ReportComponent;
    let fixture: ComponentFixture<ReportComponent>;
    let reportServiceSpy: jasmine.SpyObj<ReportService>;
    beforeEach(async () => {
        const spy = jasmine.createSpyObj('ReportService', ['getReport']);
        await TestBed.configureTestingModule({
            declarations: [ReportComponent],
            imports: [
                FormsModule,
                HttpClientTestingModule,
                FormsModule,
                HttpClientTestingModule,
                MatDatepickerModule,
                MatFormFieldModule,
                MatInputModule,
                MatNativeDateModule,
                BrowserAnimationsModule,
                MatTableModule
            ],
            providers: [{ provide: ReportService, useValue: spy }],
            schemas: [CUSTOM_ELEMENTS_SCHEMA],
        }).compileComponents();
        fixture = TestBed.createComponent(ReportComponent);
        component = fixture.componentInstance;
        reportServiceSpy = TestBed.inject(ReportService) as jasmine.SpyObj<ReportService>;
        fixture.detectChanges();
    });
    it('should create the component', () => {
        expect(component).toBeTruthy();
    });
    describe('getClientData', () => {
        it('should set error if identification is empty', () => {
            component.identification = '';
            component.getClientData();
            expect(component.errorMessage).toBe('Ingrese un número de identificación');
        });
        it('should set error if startDate is invalid', () => {
            component.identification = '1234567890';
            component.startDate = new Date('invalid');
            component.getClientData();
            expect(component.errorMessage).toBe('Ingrese fecha de inicio');
        });
        it('should set error if endDate is invalid', () => {
            component.identification = '1234567890';
            component.startDate = new Date();
            component.endDate = new Date('invalid');
            component.getClientData();
            expect(component.errorMessage).toBe('Ingrese fecha de inicio');
        });
        it('should call reportService and transform data on success', () => {
            const mockResponse = {
                data: {
                    'acc123': [{ date: '2024-01-01', transactionType: 'DEP', description: 'Ingreso', amount: 1000, balance: 1500 }]
                }
            };
            reportServiceSpy.getReport.and.returnValue(of(mockResponse));
            component.identification = '1234567890';
            component.startDate = new Date();
            component.endDate = new Date();
            component.getClientData();
            expect(reportServiceSpy.getReport).toHaveBeenCalled();
            expect(component.data.length).toBe(1);
            expect(component.data[0].accountId).toBe('acc123');
        });
        it('should set error message from service error', () => {
            const mockError = {
                error: {
                    message: 'Error del servidor'
                }
            };
            reportServiceSpy.getReport.and.returnValue(throwError(() => mockError));
            component.identification = '1234567890';
            component.startDate = new Date();
            component.endDate = new Date();
            component.getClientData();
            expect(component.errorMessage).toBe('Error del servidor');
        });
    });
    describe('transformData', () => {
        it('should transform API data correctly', () => {
            const response = {
                data: {
                    'acc1': [{ transactionType: 'DEP' }],
                    'acc2': [{ transactionType: 'RET' }]
                }
            };
            component.transformData(response);
            expect(component.data.length).toBe(2);
            expect(component.data[0].accountId).toBe('acc1');
            expect(component.data[1].accountId).toBe('acc2');
        });
    });
    describe('validateIdentification', () => {
        it('should strip non-numeric and limit to 10 digits', () => {
            const mockEvent = {
                target: {
                    value: 'abc1234567890'
                }
            } as any;
            component.validateIdentification(mockEvent);
            expect(component.identification).toBe('1234567890');
        });
    });
    describe('exportToPDF', () => {
        it('should not throw error if table is not found', () => {
            spyOn(document, 'querySelector').and.returnValue(null);
            expect(() => component.exportToPDF()).not.toThrow();
        });
    });
});
