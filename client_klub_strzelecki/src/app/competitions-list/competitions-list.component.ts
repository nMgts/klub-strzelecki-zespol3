import {AfterViewInit, ChangeDetectorRef, Component, OnInit} from '@angular/core';
import {Competition} from "../interfaces/competition";
import {CompetitionService} from "../services/competition.service";
import {UsersService} from "../services/users.service";
import {MatDialog} from "@angular/material/dialog";
import {CompetitionDetailsDialogComponent} from "../competition-details-dialog/competition-details-dialog.component";

@Component({
  selector: 'app-competitions-list',
  templateUrl: './competitions-list.component.html',
  styleUrls: ['./competitions-list.component.css']
})
export class CompetitionsListComponent implements AfterViewInit, OnInit {
  competitions: Competition[] = [];
  visible: boolean = false;
  CompetitionId: number | undefined;
  errorMessage: string = '';

  isAdmin:boolean = false;
  userEmail:string = '';

  constructor(
    private competitionService: CompetitionService,
    private cd: ChangeDetectorRef,
    private userService: UsersService,
    private dialog: MatDialog) {}

  ngAfterViewInit(): void {
    this.cd.detectChanges();
  }

  private async loadCompetitions() {
    try {
      const token: any = localStorage.getItem('token');
      const response = await this.competitionService.getAllCompetitions(token);
      if (response) {
        this.competitions = response;
        this.cd.detectChanges();
      } else {
        this.showError('No users found.');
      }
    } catch (error: any) {
      this.showError(error.message);
    }
  }

  async signUpForCompetition(id?: number) {
    if (typeof id !== 'number') {
      this.showError('Invalid competition ID');
      return;
    }
    try {
      const token: any = localStorage.getItem('token');
      const response = await this.competitionService.signupShooterToCompetition(id, token);
      if (response) {
        console.log('user assigned')
      } else {
        this.showError('Error assigning shooters.');
      }
    } catch (error: any) {
      this.showError(error.message);
    }
    this.loadCompetitions();
  }

  async signOffFromCompetition(id?: number) {
    if (typeof id !== 'number') {
      this.showError('Invalid competition ID');
      return;
    }
    try {
      const token: any = localStorage.getItem('token');
      const response = await this.competitionService.signoffShooterFromCompetition(id, token);
      if (response) {
        console.log('User sign off')
      } else {
        this.showError('Error assigning shooters.');
      }
    } catch (error: any) {
      this.showError(error.message);
    }
    this.loadCompetitions();
  }

  ngOnInit(): void {
    this.isAdmin = this.userService.isAdmin();
    const token: any = localStorage.getItem('token');
    if (token) {
      this.userService.getYourProfile(token).then(
        (userData: any) => {
          this.userEmail = userData.user.email;
          this.loadCompetitions();
        },
        (error: any) => {
          console.error('Failed to fetch user details:', error);
          this.showError('Failed to fetch user details.');
        }
      );
    } else {
      console.error('Token not found in local storage.');
    }
  }

  deleteCompetition(id?: number) {

  }

  openDetails(competition: Competition) {
    this.dialog.open(CompetitionDetailsDialogComponent, {
      width: '400px',
      data: {
        id: competition.id,
        emails: competition.emails
      }
    });
  }

  showError(mess: string) {
    this.errorMessage = mess;
    setTimeout(() => {
      this.errorMessage = ''
    }, 3000)
  }
}
