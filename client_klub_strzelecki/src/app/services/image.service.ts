import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import { Observable } from 'rxjs';
import {Imagedata} from "../interfaces/imagedata";

@Injectable({
  providedIn: 'root'
})
export class ImageService {

  private baseUrl = 'http://localhost:8080/api/images';
  private deleteUrl = 'http://localhost:8080/api/images/delete';

  constructor(private http: HttpClient) { }

  getAllImages(): Observable<Imagedata[]> {
    return this.http.get<Imagedata[]>(`${this.baseUrl}/all`);
  }

  addImage(name: string, image: File): Observable<ImageData> {
    const formData = new FormData();
    formData.append('name', name);
    formData.append('image', image);

    return this.http.post<ImageData>(`${this.baseUrl}/add`, formData, {
      headers: new HttpHeaders({
        'Authorization': 'Bearer ' + localStorage.getItem('token') // Dostosuj sposób przekazywania tokena, jeśli jest używany
      })
    });
  }

  deleteImage(id: number): Observable<void> {
    return this.http.delete<void>(`${this.deleteUrl}/${id}`, {
      headers: new HttpHeaders({
        'Authorization': 'Bearer ' + localStorage.getItem('token') // Dostosuj sposób przekazywania tokena, jeśli jest używany
      })
    });
  }
}


