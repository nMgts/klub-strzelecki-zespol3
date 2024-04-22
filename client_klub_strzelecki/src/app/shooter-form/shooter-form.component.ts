import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { ShooterService } from '../services/shooter.service';
import { Shooter } from '../entities/shooter';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-shooter-form',
  standalone: true,
  imports: [
    FormsModule
  ],
  templateUrl: './shooter-form.component.html',
  styleUrl: './shooter-form.component.css'
})
export class ShooterFormComponent implements OnInit {
  shooter: Shooter = {
    id: 0,
    firstName: '',
    lastName: '',
    email: ''
  };

  constructor (
    private acitvatedRoute: ActivatedRoute,
    private shooterService: ShooterService
  ) {}

  ngOnInit(): void {
    this.shooter = {
      id: this.acitvatedRoute.snapshot.params['id'],
      firstName: 'Andrzej',
      lastName: 'Kowalski',
      email: 'andrzej.kowalski@localhost'
    }

    // odkomentuj gdy endpoint będzie gotowy i usuń powyższe

    // this.shooterService.getShooter(this.acitvatedRoute.snapshot.params['id'])
    //   .subscribe({
    //     next: (data) => {
    //       this.shooter = data;
    //     }
    //   })
  }

  save() {
    this.shooterService.editShooter(this.shooter);
  }
}
