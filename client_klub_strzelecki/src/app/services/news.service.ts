import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { News } from '../interfaces/news';

@Injectable({
  providedIn: 'root'
})
// Service for handling CRUD operations on news
export class NewsService {

  private baseUrl = 'http://localhost:8080/api/news/all';
  private deleteUrl = 'http://localhost:8080/api/news/delete';

  constructor(private http: HttpClient) {}

  getNews(): Observable<News[]> {
    return this.http.get<News[]>(this.baseUrl);
  }

  deleteNews(newsId: number): Observable<News> {
    const url = `${this.deleteUrl}/${newsId}`;
    return this.http.delete<News>(url);
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


