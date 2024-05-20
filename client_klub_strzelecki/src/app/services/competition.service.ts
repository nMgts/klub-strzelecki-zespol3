import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Competition} from "../interfaces/competition";

@Injectable({
  providedIn: 'root'
})
export class CompetitionService {

  private baseUrl = 'http://localhost:8080/api/competition/all';

  constructor(private http: HttpClient) {}

  getCompetition(): Observable<Competition[]> {
    return this.http.get<Competition[]>(this.baseUrl);
  }
}
