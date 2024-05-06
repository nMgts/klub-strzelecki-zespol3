import { Injectable } from '@angular/core';
import { HttpClient } from "@angular/common/http";
import { Observable } from "rxjs";
import { Shooter } from "../interfaces/shooter";

@Injectable({
  providedIn: 'root'
})
export class ShootersService {

  private baseURL = "http://localhost:8080/shooter/all";
  private deleteURL = "http://localhost:8080/shooter/delete"

  constructor(private http: HttpClient) { }

  getShootersList(): Observable<Shooter[]> {
    return this.http.get<Shooter[]>(`${this.baseURL}`);
  }

  deleteShooter(shooterId: number): Observable<Shooter> {
    const url = `${this.deleteURL}/${shooterId}`;
    return this.http.delete<Shooter>(url);
  }
}
