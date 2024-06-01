import { AfterViewInit, ChangeDetectorRef, Component, ViewChild, OnInit } from '@angular/core';

import { UsersService } from '../services/users.service';
import {Router} from "@angular/router";

@Component({
  selector: 'app-users',
  templateUrl: './users.component.html',
  styleUrl: './users.component.css'
})

export class UsersComponent implements AfterViewInit, OnInit {
  users: any[] = [];
  errorMessage: string = ''
  visible: boolean = false;
  userId: number| undefined

  constructor(
    private userService: UsersService,
    private cd: ChangeDetectorRef,
    private router: Router) {}

  ngAfterViewInit(): void {
    this.cd.detectChanges();
  }

  ngOnInit(): void {
    console.log("UsersComponent is initialized halo");
    this.loadUsers();
  }

  async loadUsers() {
    try {
      const token: any = localStorage.getItem('token');
      const response = await this.userService.getAllUsers(token);
      if (response /*&& response.statusCode === 200 && response.userList*/) {
        this.users = response//.userList;
      } else {
        this.showError('No users found.');
      }
    } catch (error: any) {
      this.showError(error.message);
    }
  }
  /*
  public onDeleteUser(id: number): void {
    console.log('Attempting to delete user with id:');  // Check if ID is correct
    this.userService.deleteUser(id).subscribe({
      next: (response) => {
        console.log('User deleted successfully', response);
        this.users_list = this.users_list.filter(user => user.id !== id);
      },
      error: (err) => {
        console.error('Error deleting user:', err);
      }
    });
  }
*/
  changeNlForP(text: string): string {
    let editedText = '<p>' + text;
    editedText = editedText.replace(/\n/g, '</p><p>');
    editedText += '</p>';
    return editedText;
  }

  editUsers(id: number) {
    this.router.navigate(['users/edit', id]);
  }

  async deleteUser(userId: string) {
    try {
      const token: any = localStorage.getItem('token');
      await this.userService.deleteUser(userId, token);
      this.loadUsers();
    } catch (error: any) {
      this.showError(error.message);
    }
  }
  showDialog(id?: number)
  {
    console.log("show dialog");
    this.visible = true;
    this.userId = id;
  }

  showError(mess: string) {
    this.errorMessage = mess;
    setTimeout(() => {
      this.errorMessage = ''
    }, 3000)
  }
}
