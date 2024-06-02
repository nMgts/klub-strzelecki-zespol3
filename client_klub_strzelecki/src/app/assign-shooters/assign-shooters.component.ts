import { Component, OnInit } from '@angular/core';
import { CompetitionService } from "../services/competition.service";
import { ShootersService } from "../services/shooter.service";
import { ActivatedRoute, Router } from "@angular/router";

@Component({
  selector: 'app-assign-shooters',
  templateUrl: './assign-shooters.component.html',
  styleUrls: ['./assign-shooters.component.css']
})
export class AssignShootersComponent implements OnInit {
  shooters: { id: number; first_name: string; last_name: string; email: string }[] = [];
  selectedShooters: Set<number> = new Set();
  errorMessage: string = '';
  competitionId: number = 0;

  constructor(
    private shooterService: ShootersService,
    private competitionService: CompetitionService,
    private router: Router,
    private route: ActivatedRoute
  ) { }

  ngOnInit(): void {
    // @ts-ignore
    this.competitionId = +this.route.snapshot.paramMap.get('id');
    this.loadShooters();
  }

  async loadShooters() {
    try {
      const token: any = localStorage.getItem('token');
      const response = await this.shooterService.getAllShooters(token);
      if (response) {
        this.shooters = response;
      } else {
        this.showError("No shooters found.");
      }
    } catch (error: any) {
      this.showError(error.message);
    }
  }

  onCheckboxChange(shooterId: number, event: Event): void {
    const isChecked = (event.target as HTMLInputElement).checked;
    if (isChecked) {
      this.selectedShooters.add(shooterId);
    } else {
      this.selectedShooters.delete(shooterId);
    }
  }

  async assignShooters() {
    try {
      const token: any = localStorage.getItem('token');
      const shooterIds = Array.from(this.selectedShooters);
      const response = await this.competitionService.assignMultipleShootersToCompetition(this.competitionId, shooterIds, token).toPromise();
      if (response) {
        // Handle success
      } else {
        this.showError('Error assigning shooters.');
      }
      this.goToCompetitions();
    } catch (error: any) {
      this.showError(error.message);
      this.goToCompetitions();
    }
  }

  goToCompetitions() {
    this.router.navigate(['/competitions']);
  }

  showError(message: string) {
    this.errorMessage = message;
    setTimeout(() => {
      this.errorMessage = '';
    }, 3000);
  }
}
