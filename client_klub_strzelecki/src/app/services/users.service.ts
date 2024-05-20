import { Injectable } from '@angular/core';
import { HttpClient } from "@angular/common/http";
import { Observable } from "rxjs";
import { User } from "../interfaces/user";

@Injectable({
  providedIn: 'root'
})
export class UsersService {

  private baseUrl = 'http://localhost:8080/api/user/all';
  private deleteUrl = 'http://localhost:8080/api/user/delete';
  private postUrl = 'http://localhost:8080/api/user/add';
  private putUrl = 'http://localhost:8080/api/user/edit';
  private getUrl = 'http://localhost:8080/api/user'

  constructor(private http: HttpClient) {}

  getUser(): Observable<User[]> {
    return this.http.get<User[]>(this.baseUrl);
  }

  deleteUser(userId: number): Observable<User> {
    const url = `${this.deleteUrl}/${userId}`;
    return this.http.delete<User>(url);
  }

  getUserById(userId: number): Observable<User> {
    const url = `${this.getUrl}/${userId}`;
    return this.http.get<User>(url);
  }

  addUser(user?: User): Observable<User> {
    return this.http.post<User>(this.postUrl, user);
  }

  editUser(id: number, user: User) {
    return this.http.put(`${this.putUrl}/${id}`, user);
  }
}
