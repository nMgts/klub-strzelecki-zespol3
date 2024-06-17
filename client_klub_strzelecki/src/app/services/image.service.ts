import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import {Imagedata} from "../interfaces/imagedata";

@Injectable({
  providedIn: 'root'
})
export class ImageService {

  private baseUrl = 'http://localhost:8080/api/images';

  constructor(private http: HttpClient) { }

  getAllImages(): Observable<Imagedata[]> {
    return this.http.get<Imagedata[]>(`${this.baseUrl}/all`);
  }
}


