import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import { Observable } from "rxjs";
import { Shooter } from "../interfaces/shooter";

@Injectable({
  providedIn: 'root'
})
export class ShootersService {

  private baseUrl = 'http://localhost:8080/api/shooter/all';
  private deleteUrl = 'http://localhost:8080/api/shooter/delete';
  private postUrl = 'http://localhost:8080/api/shooter/add';
  private putUrl = 'http://localhost:8080/api/shooter/edit';
  private getUrl = 'http://localhost:8080/api/shooter'

  constructor(private http: HttpClient) {}

  getAllShooters(token:string):Promise<any> {
    const url = `${this.baseUrl}`;
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

  deleteShooter(shooterId: number | undefined, token: string):Promise<any> {
    const url = `${this.deleteUrl}/${shooterId}`;
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

  getShooterById(shooteId:string, token: string):Promise<any> {
    const url = `${this.getUrl}/${shooteId}`;
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

  addShooter(shooterData:any, token:string):Promise<any> {
    const url = `${this.postUrl}`;
    const headers = new HttpHeaders({
      'Authorization': `Bearer ${token}`
    })
    try {
      const response = this.http.post<any>(url, shooterData, {headers}).toPromise()
      return response;
    } catch (error) {
      throw error
    }
  }

  async editShooter(shooterId: string | null, shooterData: any, token: string):Promise<any> {
    const url = `${this.putUrl}/${shooterId}`;
    const headers = new HttpHeaders({
      'Authorization': `Bearer ${token}`
    })
    try {
      const response = this.http.put<any>(url, shooterData, {headers}).toPromise()
      return response;
    } catch (error) {
      throw error
    }
  }
}
