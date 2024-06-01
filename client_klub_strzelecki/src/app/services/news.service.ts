import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import { Observable } from 'rxjs';
import { News } from '../interfaces/news';

@Injectable({
  providedIn: 'root'
})
// Service for handling CRUD operations on news
export class NewsService {

  private baseUrl = 'http://localhost:8080/api/news/all';
  private deleteUrl = 'http://localhost:8080/api/news/delete';
  private postUrl = 'http://localhost:8080/api/news/add';
  private putUrl = 'http://localhost:8080/api/news/edit';
  private getUrl = 'http://localhost:8080/api/news'

  constructor(private http: HttpClient) {}

  getNews(): Observable<News[]> {
    return this.http.get<News[]>(this.baseUrl);
  }

  async deleteNews(newsId: number | undefined, token: string):Promise<any> {
    const url = `${this.deleteUrl}/${newsId}`;
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

  async getNewsById(newsId:string, token: string):Promise<any> {
    const url = `${this.getUrl}/${newsId}`;
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

  async addNews(newsData:any, token:string):Promise<any> {
    const url = `${this.postUrl}`;
    const headers = new HttpHeaders({
      'Authorization': `Bearer ${token}`
    })
    try {
      const response = this.http.post<any>(url, newsData, {headers}).toPromise()
      return response;
    } catch (error) {
      throw error
    }
  }

  async updateNews(newsId: string | null, newsData: any, token: string):Promise<any> {
    const url = `${this.putUrl}/${newsId}`;
    const headers = new HttpHeaders({
      'Authorization': `Bearer ${token}`
    })
    try {
      const response = this.http.put<any>(url, newsData, {headers}).toPromise()
      return response;
    } catch (error) {
      throw error
    }
  }
}

// import { Injectable } from '@angular/core';
// import { HttpClient } from "@angular/common/http";
// import { Observable } from "rxjs";
// import { News } from "../entities/news";

// @Injectable({
//   providedIn: 'root'
// })
// export class NewsService {

//   private baseURL = "http://localhost:8080/api/news/all";

//   constructor(private httpClient: HttpClient) { }

//   getNewsList(): Observable<News[]> {
//     return this.httpClient.get<News[]>(`${this.baseURL}`);
//   }

//   deleteNews(id: number): Observable<any> {
//     return this.httpClient.delete(`${this.baseURL}/delete/${id}`);
//   }
// }


