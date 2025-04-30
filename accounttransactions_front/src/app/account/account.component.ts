import { Component } from '@angular/core';
import { ClientService } from '../client/service/client.service';
import { AccountService } from './service/account.service';

@Component({
  selector: 'app-account',
  standalone: false,
  templateUrl: './account.component.html',
  styleUrl: './account.component.css'
})
export class AccountComponent {
  identification: string = '';
  errorMessage: string = '';
  clientData: any = null;
  showForm: boolean = false;
  newAccount = {
    name: '',
    id: '',
    accountType: 'AHORRO',
    initialBalance: '',
    client: { identification: '' }
  }

  constructor(private clientService: ClientService, private accountService: AccountService) { }
  // GETDATA
  getClientData(): void {
    if (!this.identification.trim()) {
      this.errorMessage = 'Ingrese un número de identificación';
      return;
    }
    this.errorMessage = '';
    this.clientService.getClientById(this.identification).subscribe(
      (response) => {
        if (response.success) {
          this.showForm = true;
          this.clientData = response.data;
          this.identification = '';
          this.newAccount.name = response.data.name + ' ' + response.data.lastName;
          this.newAccount.id = response.data.identification;
          this.newAccount.client.identification = response.data.identification;
        }
      },
      (error) => {
        this.showForm = false;
        this.errorMessage = error.error.message;
        this.clientData = null;
      }
    );
  }
  // VALIDACIONES
  validateIdentification(event: any): void {
    const input = event.target as HTMLInputElement;
    let inputValue = input.value.replace(/[^0-9]/g, '');
    if (inputValue.length > 10) {
      inputValue = inputValue.slice(0, 10);
    }
    input.value = inputValue;
    this.identification = inputValue;
  }
  //VALIDACIONES
  validateAmount(event: any): void {
    const input = event.target as HTMLInputElement;
    let inputValue = input.value;
    inputValue = inputValue.replace(/[^0-9]/g, '');
    const decimalCount = (inputValue.match(/\./g) || []).length;
    if (decimalCount > 1) {
      inputValue = inputValue.replace(/\.(?=.*\.)/, '');
    }
    input.value = inputValue;
    this.newAccount.initialBalance = inputValue;
  }
  //ONSUBMIT
  onSubmit() {
    if (!this.newAccount.name) {
      this.errorMessage = 'Nombre es obligatoria';
      return;
    }
    if (!this.newAccount.initialBalance) {
      this.errorMessage = 'Monto es obligatorio';
      return;
    }
    this.accountService.createAccount(this.newAccount).subscribe(
      response => {
        this.errorMessage = response.message;
        this.resetForm();
      },
      error => {
        this.resetForm();
        if (error.error.message.errors) {
          this.errorMessage = error.error.message.errors;
        } else if (error.error.message) {
          this.errorMessage = error.error.message;
        } else {
          this.errorMessage = error;
        }
      }
    );
  }
  // RESET
  resetForm() {
    this.newAccount = {
      name: '',
      id: '',
      accountType: 'AHORRO',
      initialBalance: '',
      client: { identification: '' }
    }
  }
}