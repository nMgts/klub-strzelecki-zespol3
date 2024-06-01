import { Component } from '@angular/core';
import {UsersService} from "../services/users.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrl: './register.component.css'
})
export class RegisterComponent {
  formData: any = {
    first_name: '',
    last_name: '',
    email: '',
    password: '',
    role: ''
  };
  errorMessage: string = '';

  constructor(private readonly userService: UsersService, private readonly router: Router) {}

  async handleSubmit() {
    if (!this.formData.first_name || !this.formData.last_name || !this.formData.email || !this.formData.password || !this.formData.role) {
      this.showError('Uzupełnij wszystkie pola.');
      return;
    }

    const confirmRegistration = confirm('Czy na pewno chcesz się zarejestrować?')
    if (!confirmRegistration) {
      return;
    }
    try {
      const token = localStorage.getItem('token');
      if (!token) {
        throw new Error('No token found');
      }

      const response = await this.userService.register(this.formData, token);
      if (response.statusCode === 200) {
        this.router.navigate(['/news']);
      } else {
        this.showError(response.message);
      }
    } catch (error: any) {
      this.showError(error.message);
    }
  }

  showError(messaga: string) {
    this.errorMessage = messaga;
    setTimeout(() => {
      this.errorMessage = '';
    }, 3000);
  }
}
