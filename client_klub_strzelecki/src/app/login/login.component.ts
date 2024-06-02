import { Component } from '@angular/core';
import {UsersService} from "../services/users.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent {
  email: string = ''
  password: string = ''
  errorMessage: string = ''

  constructor(private  readonly  userService: UsersService, private router: Router) {}

  async handleSubmit() {
    if (!this.email || !this.password) {
      this.showError("Email and Password is required");
      return
    }

    try {
      const response = await this.userService.login(this.email, this.password);
      if(response.statusCode === 200) {
        localStorage.setItem('token', response.token)
        localStorage.setItem('role', response.role)
      } else {
        this.showError(response.message)
      }
      window.location.reload();
    } catch (error:any) {
      this.showError(error.message)
    }
  }

  showError(mess: string) {
    this.errorMessage = mess;
    setTimeout(()=>{
      this.errorMessage = ''
    }, 3000)
  }

}
