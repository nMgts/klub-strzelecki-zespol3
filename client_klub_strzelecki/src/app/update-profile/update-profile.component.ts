import {Component, OnInit} from '@angular/core';
import {UsersService} from "../services/users.service";
import {ActivatedRoute, Router} from "@angular/router";

@Component({
  selector: 'app-update-profile',
  templateUrl: './update-profile.component.html',
  styleUrl: './update-profile.component.css'
})
export class UpdateProfileComponent implements OnInit {
  userId: any;
  userData: any = {}
  errorMessage:string = ''

  constructor(private readonly userService: UsersService,
              private readonly router: Router, private readonly route: ActivatedRoute) {}

  async getUserById() {
    this.userId = this.route.snapshot.paramMap.get('id')
    const token = localStorage.getItem('token')
    if (!this.userId || !token) {
      this.showError("User ID or token is required")
      return;
    }
    try {
      let userDataResponse = await this.userService.getUserById(this.userId, token)
      const {first_name, last_name, email, role} = userDataResponse
      this.userData = {first_name, last_name, email, role};
    } catch (error: any) {
      this.showError(error.message);
    }
  }

  async updateUser() {
    try {
      const token = localStorage.getItem('token')
      if(!token) {
        throw new Error("Token not found")
      }
      const res = await this.userService.updateUser(this.userId, this.userData, token);

      if(res.statusCode === 200) {
        this.router.navigate(['/profile'])
      } else {
        this.showError(res.message)
      }
    } catch (error:any) {
      this.showError(error.message)
    }
  }

  ngOnInit(): void {
    this.getUserById()
  }

  showError(mess: string) {
    this.errorMessage = mess;
    setTimeout(() => {
      this.errorMessage = ''
    }, 3000)
  }
}
