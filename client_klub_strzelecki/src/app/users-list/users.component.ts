import { AfterViewInit, ChangeDetectorRef, Component, OnInit } from '@angular/core';
import { UsersService } from '../services/users.service';
import { Router } from "@angular/router";

@Component({
  selector: 'app-users',
  templateUrl: './users.component.html',
  styleUrl: './users.component.css'
})

export class UsersComponent implements AfterViewInit, OnInit {
  users: any[] = [];
  filteredUsers: any[] = [];  // zmienna dla przefiltrowanej listy użytkowników
  searchQuery: string = '';   // zmienna do wyszukiwania
  errorMessage: string = '';
  visible: boolean = false;
  userId: number | undefined;

  // Zmienne do paginacji
  currentPage: number = 1;
  itemsPerPage: number = 5;
  totalPages: number = 1;

  constructor(
    private userService: UsersService,
    private cd: ChangeDetectorRef,
    private router: Router
  ) {}

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
      if (response) {
        this.users = response.userList;
        this.filteredUsers = this.users;  // Inicjalizacja przefiltrowanej listy użytkowników
        this.updatePagination();  // Aktualizacja paginacji
      } else {
        this.showError('No users found.');
      }
    } catch (error: any) {
      this.showError(error.message);
    }
  }

  // metoda do filtrowania użytkowników na podstawie zapytania
  filterUsers() {
    this.filteredUsers = this.users.filter(user =>
      user.first_name.toLowerCase().includes(this.searchQuery.toLowerCase()) ||
      user.last_name.toLowerCase().includes(this.searchQuery.toLowerCase()) ||
      user.email.toLowerCase().includes(this.searchQuery.toLowerCase())
    );
    this.currentPage = 1;  // Zresetowanie do pierwszej strony po filtracji
    this.updatePagination();  // Aktualizacja liczby stron po filtracji
  }

  // metoda do aktualizacji liczby stron
  updatePagination() {
    this.totalPages = Math.ceil(this.filteredUsers.length / this.itemsPerPage);
  }

  // obsługa przełączania stron
  nextPage() {
    if (this.currentPage < this.totalPages) {
      this.currentPage++;
    }
  }

  previousPage() {
    if (this.currentPage > 1) {
      this.currentPage--;
    }
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

  showDialog(id?: number) {
    console.log("show dialog");
    this.visible = true;
    this.userId = id;
  }

  showError(mess: string) {
    this.errorMessage = mess;
    setTimeout(() => {
      this.errorMessage = ''
    }, 3000);
  }
}
