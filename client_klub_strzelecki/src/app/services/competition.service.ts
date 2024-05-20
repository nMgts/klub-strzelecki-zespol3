import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Competition} from "../interfaces/competition";
import {News} from "../interfaces/news";

@Injectable({
  providedIn: 'root'
})
export class CompetitionService {

  private baseUrl = 'http://localhost:8080/api/competition/all';
  private postUrl = 'http://localhost:8080/api/competition/save';

  constructor(private http: HttpClient) {}

  getCompetition(): Observable<Competition[]> {
    return this.http.get<Competition[]>(this.baseUrl);
  }

  addCompetition(competition?: Competition): Observable<Competition> {
    return this.http.post<Competition>(this.postUrl, competition);
  }
}
