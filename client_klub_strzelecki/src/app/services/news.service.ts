import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
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

  constructor(private http: HttpClient) {}

  getNews(): Observable<News[]> {
    return this.http.get<News[]>(this.baseUrl);
  }

  deleteNews(newsId: number): Observable<News> {
    const url = `${this.deleteUrl}/${newsId}`;
    return this.http.delete<News>(url);
  }

  getNewsById(newsId: number): Observable<News> {
    const url = `${this.baseUrl}/${newsId}`;
    return this.http.get<News>(url);
  }

  addNews(news?: News): Observable<News> {
    return this.http.post<News>(this.postUrl, news);
  }

  editNews(id: number, news: News) {
    return this.http.put(`${this.putUrl}/${id}`, news);
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


