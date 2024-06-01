import {AfterViewInit, ChangeDetectorRef, Component} from '@angular/core';
import {Competition} from "../interfaces/competition";
import {CompetitionService} from "../services/competition.service";
import {UsersService} from "../services/users.service";

@Component({
  selector: 'app-competitions-list',
  templateUrl: './competitions-list.component.html',
  styleUrl: './competitions-list.component.css'
})
export class CompetitionsListComponent implements AfterViewInit {
  competitions: Competition[] = [];
  visible: boolean = false;
  CompetitionId: number| undefined
  errorMessage: string = '';

  isAdmin:boolean = false;

  constructor(private competitionService: CompetitionService, private cd: ChangeDetectorRef, private userService: UsersService,) {
  }

  ngAfterViewInit(): void {
    this.cd.detectChanges();
  }

  private async loadCompetitions() {
    try {
      const token: any = localStorage.getItem('token');
      const response = await this.competitionService.getAllCompetitions(token);
      if (response) {
        this.competitions = response;
      } else {
        this.showError('No users found.');
      }
    } catch (error: any) {
      this.showError(error.message);
    }
  }

  deleteCompetition(id? : number) {
  }

  ngOnInit(): void {
    this.isAdmin = this.userService.isAdmin();
    this.loadCompetitions();
  }

  showError(mess: string) {
    this.errorMessage = mess;
    setTimeout(() => {
      this.errorMessage = ''
    }, 3000)
  }
}
