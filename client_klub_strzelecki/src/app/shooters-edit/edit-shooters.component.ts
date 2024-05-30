import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import { Shooter } from '../interfaces/shooter';
import { ShootersService } from '../services/shooters.service';

@Component({
  selector: 'app-edit-shooters',
  templateUrl: './edit-shooters.component.html',
  styleUrls: ['./edit-shooters.component.css']
})
export class EditShooterComponent implements OnInit {
  id: number;
  new_shooter: Shooter = {
    first_name: '',
    last_name: '',
    email: ''
  };

  constructor(
    private shooterService: ShootersService,
    private route: ActivatedRoute,
    private router: Router) {
    this.id = 0;
  }

  onSubmit() {
    this.shooterService.editShooter(this.id, this.new_shooter).subscribe(data => {
      this.goToShooters();
    }, error => console.log(error));
  }

  goToShooters() {
    this.router.navigate(['/shooters']);
  }

  ngOnInit(): void {
    this.id = this.route.snapshot.params['id'];
    this.shooterService.getShooterById(this.id).subscribe(data => {
      this.new_shooter = data;
    }, error => console.log(error));
  }

}
