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

  addCompetition(competitionData:any, token:string):Promise<any> {
    const url = `${this.postUrl}`;
    const headers = new HttpHeaders({
      'Authorization': `Bearer ${token}`
    })
    try {
      const response = this.http.post<any>(url, competitionData, {headers}).toPromise()
      return response;
    } catch (error) {
      throw error
    }
  }
}
