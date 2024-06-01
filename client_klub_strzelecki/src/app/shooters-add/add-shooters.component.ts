import {Component, OnInit} from '@angular/core';
import {Router} from "@angular/router";
import { Shooter } from '../interfaces/shooter';
import { ShootersService } from '../services/shooter.service';

@Component({
  selector: 'app-add-shooters',
  templateUrl: './add-shooters.component.html',
  styleUrls: ['./add-shooters.component.css']
})
export class AddShooterComponent implements OnInit {
  new_shooter: Shooter = {
    first_name: '',
    last_name: '',
    email: ''
  };
  errorMessage: string = ''

  constructor(
    private shooterService: ShootersService,
    private router: Router) {
  }

  ngOnInit(): void {
  }

  async saveShooter() {
    try {
      const token: any = localStorage.getItem('token');
      const response = await this.shooterService.addShooter(this.new_shooter, token);
      if (response) {
        this.new_shooter = response;
      } else {
        this.showError('No users found.');
      }
      this.goToShooters();
    } catch (error: any) {
      this.showError(error.message);
      this.goToShooters();
    }
  }

  goToShooters() {
    this.router.navigate(['/shooters']);
  }

  onSubmit() {
    console.log(this.new_shooter);
    this.saveShooter();
  }

  showError(mess: string) {
    this.errorMessage = mess;
    setTimeout(() => {
      this.errorMessage = ''
    }, 3000)
  }
}
