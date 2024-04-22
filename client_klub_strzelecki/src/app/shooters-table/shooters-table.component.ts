import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Shooter } from "../entities/shooter";
import {ShooterService} from "../services/shooter.service";
import { Router } from '@angular/router';

@Component({
  selector: 'app-shooters-table',
  imports: [CommonModule],
  templateUrl: './shooters-table.component.html',
  standalone: true,
  styleUrl: './shooters-table.component.css'
})
export class ShootersTableComponent implements OnInit {

  constructor (
    private shooterService: ShooterService,
    private router: Router
  ) {}

  shooters: Shooter[] = [];

  ngOnInit(): void {
    this.getShooters();
  }

  private getShooters() {
    this.shooterService.getShootersList().subscribe(data => {
      this.shooters = data;
    });
  }

  editShooter(event: any) {
    this.router.navigate(['/shooters/' + event.data.id]);
  }

  deleteShooter(shooterId: number) {
    this.shooterService.deleteShooter(shooterId);
  }
}
