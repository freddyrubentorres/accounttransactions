import { Component } from '@angular/core';
import { TransactionService } from './service/transaction.service';

@Component({
  selector: 'app-transaction',
  standalone: false,
  templateUrl: './transaction.component.html',
  styleUrl: './transaction.component.css'
})
export class TransactionComponent {
  accountNumber: string = '';
  errorMessage: string = '';
  accountData: any = null;
  showForm: boolean = false;
  newTransaction = {
    account: {accountNumber: ''},
    typeAccount: '',
    amount: '',
    description: ''
  };

  constructor(private transactionService: TransactionService) { }

  getClientData(): void {
    if (!this.accountNumber.trim()) {
      this.errorMessage = 'Por favor, ingrese numero de cuenta';
      return;
    }
    this.errorMessage = '';
    this.transactionService.getAccountNumber(this.accountNumber).subscribe(
      (response) => {
        if (response.success) {
          this.accountNumber = '';
          this.showForm = true;
          this.accountData = response.data;
          this.newTransaction.account.accountNumber =  response.data.accountNumber;
          this.newTransaction.typeAccount = response.data.accountType;
        }
      },
      (error) => {
        this.accountNumber = '';
        this.showForm = false;
        this.errorMessage = error.error.message;
      }
    );
  }
  //ONSUBMIT
  onSubmit() {
    if (!this.newTransaction.account.accountNumber) {
      this.errorMessage = 'Cuenta es obligatoria';
      return;
    }
    if (!this.newTransaction.typeAccount) {
      this.errorMessage = 'Tipo de cuenta es obligatorio';
      return;
    }
    if (!this.newTransaction.description) {
      this.errorMessage = 'Descripción es obligatoria';
      return;
    }
    if (!this.newTransaction.amount) {
      this.errorMessage = 'Costo es obligatorio';
      return;
    }
    this.transactionService.createTransaction(this.newTransaction).subscribe(
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
  //VALIDACIONES
  validateAmount(event: any): void {
    const input = event.target as HTMLInputElement;
    let inputValue = input.value;
    inputValue = inputValue.replace(/[^0-9.-]/g, '');
    const decimalCount = (inputValue.match(/\./g) || []).length;
    if (decimalCount > 1) {
      inputValue = inputValue.replace(/\.(?=.*\.)/, '');
    }
    if (inputValue.indexOf('-') !== 0) {
      inputValue = inputValue.replace(/-/g, '');
    }
    input.value = inputValue;
    this.newTransaction.amount = inputValue;
  }
  validateAccountNumber(event: any): void {
    const input = event.target as HTMLInputElement;
    let inputValue = input.value.replace(/[^0-9]/g, '');
    if (inputValue.length > 6) {
      inputValue = inputValue.slice(0, 10);
    }
    input.value = inputValue;
    this.accountNumber = inputValue;
  }
  // RESET
  resetForm() {
    this.newTransaction = {
      account: {accountNumber: ''},
      typeAccount: '',
      amount: '',
      description: ''
    }
  }
}
