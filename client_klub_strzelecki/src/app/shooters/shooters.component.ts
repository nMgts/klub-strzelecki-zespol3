import { AfterViewInit, ChangeDetectorRef, Component, OnInit } from '@angular/core';

import { ShootersService } from '../services/shooter.service';
import { Router } from "@angular/router";

@Component({
  selector: 'app-shooters',
  templateUrl: './shooters.component.html',
  styleUrl: './shooters.component.css'
})

export class ShootersComponent implements AfterViewInit, OnInit {
  shooters: any[] = [];
  visible: boolean = false;
  shooterId: number| undefined;
  errorMessage: string = '';

  constructor(
    private shooterService: ShootersService,
    private cd: ChangeDetectorRef) {}

  ngAfterViewInit(): void {
    this.cd.detectChanges();
  }

  ngOnInit(): void {
    console.log("ShootersComponent is initialized halo");
    this.loadShooters();
  }

  async loadShooters() {
    try {
      const token: any = localStorage.getItem('token');
      const response = await this.shooterService.getAllShooters(token);
      if (response ) {
        this.shooters = response
      } else {
        this.showError("No shooters found.")
      }
    } catch (error: any) {
      this.showError(error.message);
    }
  }
/*
  public onDeleteShooter(id: number): void {
    console.log('Attempting to delete shooter with id:');  // Check if ID is correct
    this.shooterService.deleteShooter(id).subscribe({
      next: (response) => {
        console.log('Shooter deleted successfully', response);
        this.shooters = this.shooters.filter(shooter => shooter.id !== id);
      },
      error: (err) => {
        console.error('Error deleting shooter:', err);
      }
    });
  }

 */

  changeNlForP(text: string): string {
    let editedText = '<p>' + text;
    editedText = editedText.replace(/\n/g, '</p><p>');
    editedText += '</p>';
    return editedText;
  }

  async deleteShooters(shooterId: number | undefined) {
    try {
      const token: any = localStorage.getItem('token');
      await this.shooterService.deleteShooter(shooterId, token);
      this.loadShooters();
      this.visible = false;
    } catch (error: any) {
      this.showError(error.message);
    }
  }
  showDialog(id?: number)
  {
    console.log("show dialog");
    this.visible = true;
    this.shooterId = id;
  }

  showError(mess: string) {
    this.errorMessage = mess;
    setTimeout(() => {
      this.errorMessage = ''
    }, 3000)
  }
}
