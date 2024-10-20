import {Component, Inject} from '@angular/core';
import {MAT_DIALOG_DATA} from "@angular/material/dialog";
import {UsersService} from "../services/users.service";
import {CompetitionService} from "../services/competition.service";

@Component({
  selector: 'app-competition-details-dialog',
  templateUrl: './competition-details-dialog.component.html',
  styleUrl: './competition-details-dialog.component.css'
})
export class CompetitionDetailsDialogComponent {
  isAdmin: boolean = false;

  constructor(
    @Inject(MAT_DIALOG_DATA) public data: any,
    private userService: UsersService,
    private competitionService: CompetitionService
  ) {
    this.isAdmin = this.userService.isAdmin();
  }

  get emails() {
    return this.data?.emails || [];
  }

  removeUser(email: string) {
    if (this.isAdmin && this.data.id) {
      const token = localStorage.getItem('token') || '';
      // Wywołujemy metodę z serwisu CompetitionService
      this.competitionService.removeUser(this.data.id, email, token).subscribe({
        next: (response) => {
          console.log('Użytkownik usunięty: ', email);
          // Usuń użytkownika z listy lokalnie po pomyślnym usunięciu z serwera
          const index = this.data.emails.indexOf(email);
          if (index > -1) {
            this.data.emails.splice(index, 1);
          }
        },
        error: (err) => {
          console.error('Błąd podczas usuwania użytkownika:', err);
        }
      });
    }
  }
}
