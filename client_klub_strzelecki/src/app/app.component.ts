import {Component, OnInit, HostListener} from '@angular/core';
import {UsersService} from "./services/users.service";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent implements OnInit {
  title = 'Shooting Club';
  isAuthenticated: boolean = false;
  isAdmin: boolean = false;
  isUser: boolean = false;
  navbarVisible: boolean = false;  // Zmienna do kontrolowania widoczności navbaru
  isSmallScreen: boolean = false;  // Dodana zmienna do sprawdzania wielkości ekranu

  constructor(private readonly userService: UsersService) {}

  // Słuchamy zmian rozmiaru okna
  @HostListener('window:resize', ['$event'])
  onResize(event: any): void {
    this.checkScreenWidth();  // Sprawdzamy wielkość ekranu przy zmianie rozmiaru
  }

  ngOnInit(): void {
    this.isAuthenticated = this.userService.isAuthenticated();
    this.isAdmin = this.userService.isAdmin();
    this.isUser = this.userService.isUser();
    this.checkScreenWidth();  // Sprawdzamy wielkość ekranu przy inicjalizacji
  }

  logout(): void {
    this.userService.logOut();
    this.isAuthenticated = false;
    this.isAdmin = false;
    this.isUser = false;
  }

  // Przełączanie widoczności navbaru
  toggleNavbar(): void {
    this.navbarVisible = !this.navbarVisible;
  }

  // Funkcja sprawdzająca wielkość ekranu
  private checkScreenWidth(): void {
    this.isSmallScreen = window.innerWidth < 730;  // Zaktualizowano na mniejsze ekrany
    if (!this.isSmallScreen) {
      this.navbarVisible = true;  // Na większych ekranach zawsze pokazujemy navbar
    } else {
      this.navbarVisible = false;  // Na mniejszych ekranach domyślnie ukrywamy navbar
    }
  }
}
