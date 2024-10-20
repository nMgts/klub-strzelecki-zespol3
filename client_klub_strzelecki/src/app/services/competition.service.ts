import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {forkJoin, Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class CompetitionService {

  private baseUrl = 'http://localhost:8080/api/competition/all';
  private postUrl = 'http://localhost:8080/api/competition/add';
  private assignUrl = 'http://localhost:8080/api/competition/assign';
  private signupUrl = 'http://localhost:8080/api/competition/signup';
  private signoffUrl = 'http://localhost:8080/api/competition/signoff';
  private removeUserUrl = 'http://localhost:8080/api/competition/removeUser'

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

  assignShooterToCompetition(competitionId: number, shooterId: number, token: string):Promise<any> {
    const url = `${this.assignUrl}/${competitionId}/${shooterId}`;
    const headers = new HttpHeaders({
      'Authorization': `Bearer ${token}`
    });
    try {
      console.log(headers);
      const response = this.http.post<any>(url, {}, {headers}).toPromise();
      return response;
    } catch (error) {
      throw error
    }
  }

  signupShooterToCompetition(competitionId: number, token: string):Promise<any> {
    const url = `${this.signupUrl}/${competitionId}`;
    const headers = new HttpHeaders({
      'Authorization': `Bearer ${token}`
    });
    try {
      console.log(this.http.post<any>(url, {}, {headers}));
      const response = this.http.post<any>(url, {}, {headers}).toPromise();
      return response;
    } catch (error) {
      throw error
    }
  }

  assignMultipleShootersToCompetition(competitionId: number, shooterIds: number[], token: string): Observable<any[]> {
    const requests = shooterIds.map(shooterId => this.assignShooterToCompetition(competitionId, shooterId, token));
    return forkJoin(requests);
  }

  signoffShooterFromCompetition(competitionId: number, token: string):Promise<any> {
    const url = `${this.signoffUrl}/${competitionId}`;
    const headers = new HttpHeaders({
      'Authorization': `Bearer ${token}`
    });
    try {
      const response = this.http.delete<any>(url, { headers }).toPromise();
      return response;
    } catch (error) {
      throw error
    }
  }

  removeUser(id: number, email: string, token: string): Observable<any> {
    const headers = new HttpHeaders({
      'Authorization': `Bearer ${token}`
    });
    const url = `${this.removeUserUrl}/${id}/${email}`
    return this.http.post(url, {}, { headers });
  }
}
