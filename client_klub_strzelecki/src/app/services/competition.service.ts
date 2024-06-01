import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable} from "rxjs";
import {Competition} from "../interfaces/competition";
import {News} from "../interfaces/news";

@Injectable({
  providedIn: 'root'
})
export class CompetitionService {

  private baseUrl = 'http://localhost:8080/api/competition/all';
  private postUrl = 'http://localhost:8080/api/competition/add';

  constructor(private http: HttpClient) {}

  getAllCompetitions(token:string):Promise<any> {
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

  addCompetition(competition?: Competition): Observable<Competition> {
    return this.http.post<Competition>(this.postUrl, competition);
  }
}
