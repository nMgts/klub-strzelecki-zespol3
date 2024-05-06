import { AfterViewInit, ChangeDetectorRef, Component, ViewChild, OnInit } from '@angular/core';

import { NgModule } from '@angular/core';

import { ShootersService } from '../services/shooters.service';
import { CommonModule } from '@angular/common';
import { Shooter } from '../interfaces/shooter';
import {Router} from "@angular/router";

@Component({
  selector: 'app-shooters',
  templateUrl: './shooters.component.html',
  styleUrls: [
  ]
})



export class ShootersComponent implements AfterViewInit {
  
  shooters_list: Shooter[] = [];

  constructor(
    private shooterService: ShootersService, 
    private cd: ChangeDetectorRef,
    private router: Router) {}

  // After init - because we need the pagination to load first
  // Fetch the shooters from the database and display them
  ngAfterViewInit(): void {
    
    // The DOM has been changed, we need to detect the changes to prevent ExpressionChangedAfterItHasBeenCheckedError
    this.cd.detectChanges();
  }
  logHello(): void {
    console.log("Hello");
  }
  ngOnInit(): void { 
    console.log("ShootersComponent is initialized halo");
    this.getShooters();
  }

  private getShooters() {
    this.shooterService.getShooter().subscribe(data => {
      this.shooters_list = data;
    });
  }
  public onDeleteShooter(id: number): void {
    console.log('Attempting to delete shooter with id:');  // Check if ID is correct
    this.shooterService.deleteShooter(id).subscribe({
      next: (response) => {
        console.log('Shooter deleted successfully', response);
        this.shooters_list = this.shooters_list.filter(shooter => shooter.id !== id);
      },
      error: (err) => {
        console.error('Error deleting shooter:', err);
      }
    });
  }

  changeNlForP(text: string): string {
    let editedText = '<p>' + text;
    editedText = editedText.replace(/\n/g, '</p><p>');
    editedText += '</p>';
    return editedText;
  }

  editShooters(id: number) {
    this.router.navigate(['shooters/edit', id]);
  }

  deleteShooters(id?: number): void {
    if (id !== undefined) {
      this.shooterService.deleteShooter(id).subscribe( data =>{
        console.log(data);
        this.getShooters();
      })
    } else {
      console.error('ID is undefined');
    }
  }
}