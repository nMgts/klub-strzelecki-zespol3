import { Injectable } from '@angular/core';
import { HttpClient } from "@angular/common/http";
import { Observable } from "rxjs";
import { Shooter } from "../entities/shooter";

@Injectable({
  providedIn: 'root'
})
export class ShooterService {

  private baseURL = "http://localhost:8080/shooters";

  constructor(private httpClient: HttpClient) { }

  getShootersList(): Observable<Shooter[]> {
    return this.httpClient.get<Shooter[]>(`${this.baseURL}`);
  }
}
