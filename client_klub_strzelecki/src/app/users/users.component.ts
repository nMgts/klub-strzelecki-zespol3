import { AfterViewInit, ChangeDetectorRef, Component, ViewChild, OnInit } from '@angular/core';

import { NgModule } from '@angular/core';

import { UsersService } from '../services/users.service';
import { CommonModule } from '@angular/common';
import { User } from '../interfaces/user';
import {Router} from "@angular/router";

@Component({
  selector: 'app-users',
  templateUrl: './users.component.html',
  styleUrl: './users.component.css'
})

export class UsersComponent implements AfterViewInit {

  users_list: User[] = [];
  visible: boolean = false;
  userId: number| undefined

  constructor(
    private userService: UsersService,
    private cd: ChangeDetectorRef,
    private router: Router) {}

  // After init - because we need the pagination to load first
  // Fetch the shooters from the database and display them
  ngAfterViewInit(): void {

    // The DOM has been changed, we need to detect the changes to prevent ExpressionChangedAfterItHasBeenCheckedError
    this.cd.detectChanges();
  }
  logHello(): void {
    console.log("Hello");
  }
  ngOnInit(): void {
    console.log("UsersComponent is initialized halo");
    this.getUsers();
  }

  private getUsers() {
    this.userService.getUser().subscribe(data => {
      this.users_list = data;
    });
  }
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

  changeNlForP(text: string): string {
    let editedText = '<p>' + text;
    editedText = editedText.replace(/\n/g, '</p><p>');
    editedText += '</p>';
    return editedText;
  }

  editUsers(id: number) {
    this.router.navigate(['users/edit', id]);
  }

  deleteUsers(): void {
    if (this.userId !== undefined) {
      this.userService.deleteUser(this.userId).subscribe( data =>{
        console.log(data);
        this.getUsers();
      })
    } else {
      console.error('ID is undefined');
    }
  }
  showDialog(id?: number)
  {
    console.log("show dialog");
    this.visible = true;
    this.userId = id;
  }
}
