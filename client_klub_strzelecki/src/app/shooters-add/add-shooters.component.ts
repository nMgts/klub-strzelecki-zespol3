import {Component, OnInit} from '@angular/core';
import {Router} from "@angular/router";
import { Shooter } from '../interfaces/shooter';
import { ShootersService } from '../services/shooters.service';

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

  constructor(
    private shooterService: ShootersService,
    private router: Router) {
  }

  ngOnInit(): void {
  }

  saveShooter() {
    this.shooterService.addShooter(this.new_shooter).subscribe( data =>{
      console.log(data);
      this.goToShooters();
    },
        error => console.log(error));
  }

  goToShooters() {
    this.router.navigate(['/shooters']);
  }

  onSubmit() {
    console.log(this.new_shooter);
    this.saveShooter();
  }
}
