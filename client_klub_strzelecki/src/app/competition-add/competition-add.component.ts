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
  constructor(
    private competitionService: CompetitionService,
    private router: Router) {
  }

  ngOnInit(): void {
  }

  saveCompetition() {
    this.competitionService.addCompetition(this.new_competition).subscribe( data =>{
        console.log(data);
        this.goToNews();
      },
      error => console.log(error));
  }

  goToNews() {
    this.router.navigate(['/competitions']);
  }

  onSubmit() {
    console.log(this.new_competition);
    this.saveCompetition();
  }
}