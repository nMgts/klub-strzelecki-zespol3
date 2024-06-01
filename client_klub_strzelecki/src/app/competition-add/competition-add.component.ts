import {Component, OnInit} from '@angular/core';
import { Competition } from "../interfaces/competition";
import { Router } from "@angular/router";
import { CompetitionService } from "../services/competition.service";

@Component({
  selector: 'app-competition-add',
  templateUrl: './competition-add.component.html',
  styleUrl: './competition-add.component.css'
})
export class CompetitionAddComponent implements OnInit {
  new_competition: Competition = {
    name: '',
    description: '',
    start_date: '',
    end_date: '',
    shooters_limit: 0
  };
  errorMessage: string = '';

  constructor(
    private competitionService: CompetitionService,
    private router: Router) {
  }

  ngOnInit(): void {
  }

  async saveCompetition() {
    try {
      const token: any = localStorage.getItem('token');
      const response = await this.competitionService.addCompetition(this.new_competition, token);
      if (response) {
        this.new_competition = response;
      } else {
        this.showError('No news found.');
      }
      this.goToNews();
    } catch (error: any) {
      this.showError(error.message);
      this.goToNews();
    }
  }

  goToNews() {
    this.router.navigate(['/competitions']);
  }

  onSubmit() {
    console.log(this.new_competition);
    this.saveCompetition();
  }

  showError(messaga: string) {
    this.errorMessage = messaga;
    setTimeout(() => {
      this.errorMessage = '';
    }, 3000);
  }
}

