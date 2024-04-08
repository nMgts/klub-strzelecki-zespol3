import { Injectable } from '@angular/core';
import { HttpClient } from "@angular/common/http";
import { Observable } from "rxjs";
import { News } from "../entities/news";

@Injectable({
  providedIn: 'root'
})
export class NewsService {

  private baseURL = "http://localhost:8080/news";

  constructor(private httpClient: HttpClient) { }

  getNewsList(): Observable<News[]> {
    return this.httpClient.get<News[]>(`${this.baseURL}`);
  }
}