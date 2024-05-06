import {AfterViewInit, ChangeDetectorRef, Component} from '@angular/core';
import {Competition} from "../interfaces/competition";
import {ShootersService} from "../services/shooters.service";
import {CompetitionService} from "../services/competition.service";

@Component({
  selector: 'app-competitions-list',
  templateUrl: './competitions-list.component.html',
  styleUrl: './competitions-list.component.css'
})
export class CompetitionsListComponent implements AfterViewInit {

  competition_list: Competition[] = [];

  constructor(private competitionService: CompetitionService, private cd: ChangeDetectorRef) {
  }

  ngAfterViewInit(): void {
    this.cd.detectChanges();
  }

  private getCompetitions() {
    this.competitionService.getCompetition().subscribe(data => {
      this.competition_list = data;
    })
  }

}
