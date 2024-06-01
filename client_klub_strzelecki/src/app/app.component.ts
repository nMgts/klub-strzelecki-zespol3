import {Component, OnInit} from '@angular/core';
import {UsersService} from "./services/users.service";

@Component({
  selector: 'app-root',

  templateUrl: './app.component.html',

  styleUrl: './app.component.css'
})
export class AppComponent implements OnInit {

  constructor(private readonly userService: UsersService) {
  }

  title = 'Shooting Club';
  isAuthenticated:boolean = false;
  isAdmin:boolean = false;
  isUser:boolean = false;

  logout():void {
    this.userService.logOut();
    this.isAuthenticated = false;
    this.isAdmin = false;
    this.isUser = false;
  }

  ngOnInit(): void {
    this.isAuthenticated = this.userService.isAuthenticated();
    this.isAdmin = this.userService.isAdmin();
    this.isUser = this.userService.isUser();
  }
}
