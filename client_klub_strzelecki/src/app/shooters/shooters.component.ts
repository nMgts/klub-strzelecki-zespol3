import { AfterViewInit, ChangeDetectorRef, Component, OnInit } from '@angular/core';
import { ShootersService } from '../services/shooter.service';
import { Router } from "@angular/router";

@Component({
  selector: 'app-shooters',
  templateUrl: './shooters.component.html',
  styleUrl: './shooters.component.css'
})

export class ShootersComponent implements AfterViewInit, OnInit {
  shooters: any[] = [];
  filteredShooters: any[] = [];  // Dodano zmienną dla przefiltrowanej listy zawodników
  searchQuery: string = '';  // Dodano zmienną dla przechowywania zapytania wyszukiwania
  visible: boolean = false;
  shooterId: number| undefined;
  errorMessage: string = '';

  // Zmienne dla paginacji
  currentPage: number = 1;
  itemsPerPage: number = 5;
  totalPages: number = 1;

  constructor(
    private shooterService: ShootersService,
    private cd: ChangeDetectorRef
  ) {}

  ngAfterViewInit(): void {
    this.cd.detectChanges();
  }

  ngOnInit(): void {
    console.log("ShootersComponent is initialized halo");
    this.loadShooters();
  }

  async loadShooters() {
    try {
      const token: any = localStorage.getItem('token');
      const response = await this.shooterService.getAllShooters(token);
      if (response) {
        this.shooters = response;
        this.filteredShooters = this.shooters;  // Inicjalizacja listy zawodników
        this.updatePagination();  // Aktualizacja paginacji po załadowaniu danych
      } else {
        this.showError("No shooters found.");
      }
    } catch (error: any) {
      this.showError(error.message);
    }
  }

  // Metoda do filtrowania zawodników na podstawie zapytania
  filterShooters() {
    this.filteredShooters = this.shooters.filter(shooter =>
      shooter.first_name.toLowerCase().includes(this.searchQuery.toLowerCase()) ||
      shooter.last_name.toLowerCase().includes(this.searchQuery.toLowerCase())
    );
    this.currentPage = 1;  // Zresetowanie na pierwszą stronę po filtracji
    this.updatePagination();  // Aktualizacja paginacji po filtracji
  }

  // Metoda do aktualizacji liczby stron
  updatePagination() {
    this.totalPages = Math.ceil(this.filteredShooters.length / this.itemsPerPage);
  }

  // Metody do obsługi zmiany stron
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

  async deleteShooters(shooterId: number | undefined) {
    try {
      const token: any = localStorage.getItem('token');
      await this.shooterService.deleteShooter(shooterId, token);
      this.loadShooters();
      this.visible = false;
    } catch (error: any) {
      this.showError(error.message);
    }
  }

  showDialog(id?: number) {
    console.log("show dialog");
    this.visible = true;
    this.shooterId = id;
  }

  showError(mess: string) {
    this.errorMessage = mess;
    setTimeout(() => {
      this.errorMessage = ''
    }, 3000);
  }
}
