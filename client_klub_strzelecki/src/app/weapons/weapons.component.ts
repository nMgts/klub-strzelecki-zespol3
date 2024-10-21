import { AfterViewInit, ChangeDetectorRef, Component, ViewChild, OnInit } from '@angular/core';

import { NgModule } from '@angular/core';

import { WeaponsService } from '../services/weapon.service';
import { CommonModule } from '@angular/common';
import { Weapon } from '../interfaces/weapon';
import {Router} from "@angular/router";
import {UsersService} from "../services/users.service";

@Component({
  selector: 'app-weapons',
  templateUrl: './weapons.component.html',
  styleUrl: './weapons.component.css'
})

// Component that displays the weapons
export class WeaponsComponent implements AfterViewInit {
  weapon_list: Weapon[] = [];
  visible: boolean = false;
  weaponId: number| undefined;
  errorMessage: string = '';

  isAdmin:boolean = false;

  constructor(
    private weaponsService: WeaponsService,
    private userService: UsersService,
    private cd: ChangeDetectorRef,
    private router: Router) {}

  // After init - because we need the pagination to load first
  // Fetch the weapons from the database and display them
  ngAfterViewInit(): void {

    // The DOM has been changed, we need to detect the changes to prevent ExpressionChangedAfterItHasBeenCheckedError
    this.cd.detectChanges();
  }

  ngOnInit(): void {
    this.isAdmin = this.userService.isAdmin();
    this.getWeapons();
  }

  private getWeapons() {
    this.weaponsService.getWeapons().subscribe(data => {
      this.weapon_list = data;
    });
  }

  changeNlForP(text: string): string {
    let editedText = '<p>' + text;
    editedText = editedText.replace(/\n/g, '</p><p>');
    editedText += '</p>';
    return editedText;
  }

  editWeapons(id: number) {
    this.router.navigate(['weapons/edit', id]);
  }

  async deleteWeapons(weaponsId: number | undefined) {
    try {
      const token: any = localStorage.getItem('token');
      await this.weaponsService.deleteWeapon(weaponsId, token);
      this.getWeapons();
      this.visible = false;
    } catch (error: any) {
      this.showError(error.message);
    }
  }

  showDialog(id?: number)
  {
    console.log("show dialog");
    this.visible = true;
    this.weaponId = id;
  }


  /*
  deleteWeapons(id: number) {
    this.weaponsService.deleteWeapon(id).subscribe( data =>{
      console.log(data);
      this.getNews();
    })
  }

   */

  showError(mess: string) {
    this.errorMessage = mess;
    setTimeout(() => {
      this.errorMessage = ''
    }, 3000)
  }
}
