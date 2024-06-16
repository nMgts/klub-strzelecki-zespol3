import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ImageService {

  private baseUrl = 'http://localhost:8080/api/images';

  constructor(private http: HttpClient) { }

  getAllImages(): Observable<any> {
    return this.http.get(`${this.baseUrl}/all`);
  }

  getImage(id: number): Observable<Blob> {
    return this.http.get(`${this.baseUrl}/image/${id}`, { responseType: 'blob' });
  }
}


