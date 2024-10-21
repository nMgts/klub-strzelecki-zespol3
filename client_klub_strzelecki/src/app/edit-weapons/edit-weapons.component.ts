import {Component, OnInit} from '@angular/core';
import {Weapon} from "../interfaces/weapon";
import {WeaponsService} from "../services/weapon.service";
import {ActivatedRoute, Router} from "@angular/router";
import {resetParseTemplateAsSourceFileForTest} from "@angular/compiler-cli/src/ngtsc/typecheck/diagnostics";

@Component({
  selector: 'app-edit-weapons',
  templateUrl: './edit-weapons.component.html',
  styleUrl: './edit-weapons.component.css'
})
export class EditWeaponsComponent implements OnInit {
  id: string | null;
  new_weapons: Weapon = {
    caliber: "", id: 0,
    name: '',
    type: ''
  };
  errorMessage: string = '';

  constructor(
    private weaponsService: WeaponsService,
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
      const res = await this.weaponsService.updateWeapons(this.id, this.new_weapons, token);

      if (res) {
        this.goToNews();
      } else {
        this.showError(res.message)
      }
    } catch (error: any) {
      this.showError(error.message)
    }
  }

  async getWeaponsById() {
    this.id = this.route.snapshot.paramMap.get('id')
    const token = localStorage.getItem('token')
    if (!this.id || !token) {
      this.showError("News ID or token is required")
      return;
    }
    try {
      let weaponDataResponse = await this.weaponsService.getWeaponById(this.id, token)
      console.log(weaponDataResponse)
      const {name, type} = weaponDataResponse
      this.new_weapons = {caliber: "", id: 0, name, type};
    } catch (error: any) {
      this.showError(error.message);
    }
  }

  goToNews() {
    this.router.navigate(['/weapons']);
  }

  ngOnInit(): void {
    this.getWeaponsById();
  }

  showError(mess: string) {
    this.errorMessage = mess;
    setTimeout(() => {
      this.errorMessage = ''
    }, 3000)
  }
}
