import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Shooter } from "../entities/shooter";
import {ShooterService} from "../services/shooter.service";

@Component({
  selector: 'app-shooters-table',
  imports: [CommonModule],
  templateUrl: './shooters-table.component.html',
  standalone: true,
  styleUrl: './shooters-table.component.css'
})
export class ShootersTableComponent implements OnInit {

  constructor(private shooterService: ShooterService) {
  }

  shooters: Shooter[] = [];

  ngOnInit(): void {
    this.getShooters();
  }
  private getShooters() {
    this.shooterService.getShootersList().subscribe(data => {
      this.shooters = data;
    });
  }
}
