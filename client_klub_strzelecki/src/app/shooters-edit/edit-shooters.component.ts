import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import { Shooter } from '../interfaces/shooter';
import { ShootersService } from '../services/shooter.service';

@Component({
  selector: 'app-edit-shooters',
  templateUrl: './edit-shooters.component.html',
  styleUrls: ['./edit-shooters.component.css']
})
export class EditShooterComponent implements OnInit {
  id: string | null;
  new_shooter: Shooter = {
    first_name: '',
    last_name: '',
    email: ''
  };
  errorMessage: string = '';

  constructor(
    private shooterService: ShootersService,
    private route: ActivatedRoute,
    private router: Router) {
    this.id = '';
  }

  async onSubmit() {
    try {
      const token = localStorage.getItem('token')
      if (!token) {
        throw new Error("Token not found")
      }
      const res = await this.shooterService.editShooter(this.id, this.new_shooter, token);

      if (res) {
        this.goToShooters();
      } else {
        this.showError(res.message)
      }
    } catch (error: any) {
      this.showError(error.message)
    }
  }

  goToShooters() {
    this.router.navigate(['/shooters']);
  }

  async getShooterById() {
    this.id = this.route.snapshot.paramMap.get('id')
    const token = localStorage.getItem('token')
    if (!this.id || !token) {
      this.showError("Shooter ID or token is required")
      return;
    }
    try {
      let shooterDataResponse = await this.shooterService.getShooterById(this.id, token)
      console.log(shooterDataResponse)
      const {first_name, last_name, email} = shooterDataResponse
      this.new_shooter = {first_name, last_name, email};
    } catch (error: any) {
      this.showError(error.message);
    }
  }

  ngOnInit(): void {
    this.getShooterById();
  }

  showError(mess: string) {
    this.errorMessage = mess;
    setTimeout(() => {
      this.errorMessage = ''
    }, 3000)
  }
}
