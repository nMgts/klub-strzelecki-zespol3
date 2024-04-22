import { Injectable } from '@angular/core';
import { HttpClient } from "@angular/common/http";
import { Observable } from "rxjs";
import { Shooter } from "../entities/shooter";

@Injectable({
  providedIn: 'root'
})
export class ShooterService {

  private baseURL = "http://localhost:8080/shooter";

  constructor(private httpClient: HttpClient) { }

  getShootersList(): Observable<Shooter[]> {
    return this.httpClient.get<Shooter[]>(`${this.baseURL + '/all'}`);
  }

  getShooter(shooterId: number) {
    return this.httpClient.get<Shooter>(this.baseURL + '/' + shooterId);
  }

  editShooter(shooter: Shooter) {
    return this.httpClient.put<Shooter>(this.baseURL, shooter);
  }

  deleteShooter(shooterId: number) {
    return this.httpClient.delete(this.baseURL + '/' + shooterId);
  }
}
