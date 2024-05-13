import { Injectable } from '@angular/core';
import { HttpClient } from "@angular/common/http";
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

  getShooter(): Observable<Shooter[]> {
    return this.http.get<Shooter[]>(this.baseUrl);
  }

  deleteShooter(shooterId: number): Observable<Shooter> {
    const url = `${this.deleteUrl}/${shooterId}`;
    return this.http.delete<Shooter>(url);
  }

  getShooterById(shooterId: number): Observable<Shooter> {
    const url = `${this.getUrl}/${shooterId}`;
    return this.http.get<Shooter>(url);
  }

  addShooter(shooter?: Shooter): Observable<Shooter> {
    return this.http.post<Shooter>(this.postUrl, shooter);
  }

  editShooter(id: number, shooter: Shooter) {
    return this.http.put(`${this.putUrl}/${id}`, shooter);
  }
}
