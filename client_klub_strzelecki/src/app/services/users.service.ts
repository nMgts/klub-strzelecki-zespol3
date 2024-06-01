import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class UsersService {

  private defaultUrl = 'http://localhost:8080/api';
  private baseUrl = 'http://localhost:8080/api/user/all';
  private deleteUrl = 'http://localhost:8080/api/user/delete';
  private postUrl = 'http://localhost:8080/api/user/add';
  private putUrl = 'http://localhost:8080/api/user/edit';
  private getUrl = 'http://localhost:8080/api/user'

  constructor(private http: HttpClient) {}

  async login(email:string, password:string):Promise<any> {
    const url = `${this.defaultUrl}/auth/login`;
    try {
      const response = this.http.post<any>(url, {email, password}).toPromise()
      return response;
    } catch (error) {
      throw error
    }
  }

  async register(userData:any, token:string):Promise<any> {
    const url = `${this.defaultUrl}/auth/register`;
    const headers = new HttpHeaders({
      'Authorization': `Bearer ${token}`
    })
    try {
      const response = this.http.post<any>(url, userData, {headers}).toPromise()
      return response;
    } catch (error) {
      throw error
    }
  }

  async getAllUsers(token:string):Promise<any> {
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

  async getYourProfile(token:string):Promise<any> {
    const url = `${this.getUrl}/get-profile`;
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

  async getUserById(userId:string, token: string):Promise<any> {
    const url = `${this.getUrl}/${userId}`;
    const headers = new HttpHeaders({
      'Authorization': `Bearer ${token}`
    })
    try {
      const response = this.http.post<any>(url, {headers}).toPromise()
      return response;
    } catch (error) {
      throw error
    }
  }

  async deleteUser(userId:string, token: string):Promise<any> {
    const url = `${this.deleteUrl}/${userId}`;
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

  async updateUser(userId:string, userData: any, token: string):Promise<any> {
    const url = `${this.putUrl}/${userId}`;
    const headers = new HttpHeaders({
      'Authorization': `Bearer ${token}`
    })
    try {
      const response = this.http.put<any>(url, userData, {headers}).toPromise()
      return response;
    } catch (error) {
      throw error
    }
  }

  /***AUTHENTICATION METHODS***/
  logOut(): void {
    if (typeof localStorage !== 'undefined') {
      localStorage.removeItem('token')
      localStorage.removeItem('role')
    }
  }

  isAuthenticated(): boolean {
    if (typeof localStorage !== 'undefined') {
      const token = localStorage.getItem('token');
      return !!token;
    }
    return false;
  }

  isAdmin(): boolean {
    if (typeof localStorage !== 'undefined') {
      const role = localStorage.getItem('role');
      return role === 'ADMIN';
    }
    return false;
  }

  isUser(): boolean {
    if (typeof localStorage !== 'undefined') {
      const role = localStorage.getItem('role');
      return role === 'USER';
    }
    return false;
  }

}
