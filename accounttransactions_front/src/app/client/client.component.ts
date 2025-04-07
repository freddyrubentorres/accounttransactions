import { Component } from '@angular/core';
import { ClientService } from './service/client.service';

@Component({
  selector: 'app-client',
  standalone: false,
  templateUrl: './client.component.html',
  styleUrl: './client.component.css'
})
export class ClientComponent {
  clientData: any = null;
  errorMessage: string = '';
  identification: string = '';
  isLoading: boolean = false;
  emailInvalid = false;
  newEmployee = {
    email: '',
    age: '',
    password: '',
    name: '',
    last_name: '',
    gender: 'M',
    identification: '',
    address: '',
    phone: ''
  };

  constructor(private clientService: ClientService) { }
  // GETDATA
  getClientData(): void {
    if (!this.identification.trim()) {
      this.errorMessage = 'Por favor, ingrese un número de identificación';
      return;
    }
    this.errorMessage = '';
    this.isLoading = true;
    this.clientService.getClientById(this.identification).subscribe(
      (response) => {
        this.isLoading = false;
        if (response.success) {
          this.clientData = response.data;
          this.identification = '';
        }
      },
      (error) => {
        this.isLoading = false;
        this.errorMessage = error.error.message;
        this.clientData = null;
      }
    );
  }
  //ONSUBMIT
  onSubmit() {
    this.clientService.createClient(this.newEmployee).subscribe(
      response => {
        this.resetForm();
        this.errorMessage=response
      },
      error => {
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
  // VALIDACIONES
  validateIdentification1(event: any): void {
    const input = event.target as HTMLInputElement;
    let inputValue = input.value.replace(/[^0-9]/g, '');
    if (inputValue.length > 10) {
      inputValue = inputValue.slice(0, 10);
    }
    input.value = inputValue;
    this.identification = inputValue;
  }
  validateTextName(event: KeyboardEvent): void {
    const charCode = event.charCode;
    if ((charCode < 65 || charCode > 90) && (charCode < 97 || charCode > 122) && charCode !== 32) {
      event.preventDefault();
    }
  }
  validateTextLastName(event: KeyboardEvent): void {
    const charCode = event.charCode;
    if ((charCode < 65 || charCode > 90) && (charCode < 97 || charCode > 122) && charCode !== 32) {
      event.preventDefault();
    }
  }
  validateAge(event: Event): void {
    const input = event.target as HTMLInputElement;
    let inputValue = input.value.replace(/[^0-9]/g, '');
    if (inputValue.length > 2) {
      inputValue = inputValue.slice(0, 2);
    }
    input.value = inputValue;
    this.newEmployee.age = inputValue;
  }
  validateIdentification(event: any): void {
    const input = event.target as HTMLInputElement;
    let inputValue = input.value.replace(/[^0-9]/g, '');
    if (inputValue.length > 10) {
      inputValue = inputValue.slice(0, 10);
    }
    input.value = inputValue;
    this.newEmployee.identification = inputValue;
  }
  validateEmail(event: Event): void {
    const input = event.target as HTMLInputElement;
    const emailPattern = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,4}$/;
    this.emailInvalid = !emailPattern.test(input.value);
  }
  validatePhone(event: any): void {
    const inputValue = event.target.value;
    const validValue = inputValue.replace(/[^0-9]/g, '');
    if (validValue.length > 10) {
      event.target.value = validValue.substring(0, 10);
    } else {
      event.target.value = validValue;
    }
    this.newEmployee.phone = event.target.value;
  }
  // RESET
  resetForm() {
    this.newEmployee = {
      email: '',
      age: '',
      password: '',
      name: '',
      last_name: '',
      gender: 'M',
      identification: '',
      address: '',
      phone: ''
    };
  }
}