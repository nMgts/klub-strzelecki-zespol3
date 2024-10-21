import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import { Observable } from 'rxjs';
import { Weapon } from '../interfaces/weapon';

@Injectable({
  providedIn: 'root'
})
// Service for handling CRUD operations on weapons
export class WeaponsService {

  private baseUrl = 'http://localhost:8080/api/weapon/all';
  private deleteUrl = 'http://localhost:8080/api/weapon/delete';
  private postUrl = 'http://localhost:8080/api/weapon/add';
  private putUrl = 'http://localhost:8080/api/weapon/edit';
  private getUrl = 'http://localhost:8080/api/weapon'

  constructor(private http: HttpClient) {}

  getWeapons(): Observable<Weapon[]> {
    return this.http.get< Weapon[]>(this.baseUrl);
  }

  async deleteWeapon(weaponsId: number | undefined, token: string):Promise<any> {
    const url = `${this.deleteUrl}/${weaponsId}`;
    const headers = new HttpHeaders({
      'Authorization': `Bearer ${token}`
    })
    try {
      const response = this.http.delete<any>(url, {headers}).toPromise()
      return response;
    } catch (error) {
      throw error
    }
  }

  async getWeaponById(weaponsId:string, token: string):Promise<any> {
    const url = `${this.getUrl}/${weaponsId}`;
    const headers = new HttpHeaders({
      'Authorization': `Bearer ${token}`
    })
    try {
      const response = this.http.get<any>(url, {headers}).toPromise()
      return response;
    } catch (error) {
      throw error
    }
  }

  async addWeapons(weaponsData:any, token:string):Promise<any> {
    const url = `${this.postUrl}`;
    const headers = new HttpHeaders({
      'Authorization': `Bearer ${token}`
    })
    try {
      const response = this.http.post<any>(url, weaponsData, {headers}).toPromise()
      return response;
    } catch (error) {
      throw error
    }
  }

  async updateWeapons(weaponsId: string | null, weaponsData: any, token: string):Promise<any> {
    const url = `${this.putUrl}/${weaponsId}`;
    const headers = new HttpHeaders({
      'Authorization': `Bearer ${token}`
    })
    try {
      const response = this.http.put<any>(url, weaponsData, {headers}).toPromise()
      return response;
    } catch (error) {
      throw error
    }
  }
}



