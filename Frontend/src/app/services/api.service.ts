import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { User } from '../models/user.model';

@Injectable({
  providedIn: 'root',
})
export class ApiService {
  private baseURL: string = 'http://localhost:8080/gymmember/members';
  constructor(private http: HttpClient) {}

  postRegistration(registerUser: User) {
    console.log('Insider Post Registeration Route');
    console.log('This is the Object Recieved', registerUser);
    return this.http.post<User>(`${this.baseURL}`, registerUser);
  }

  getRegisteredUser() {
    console.log('Indside GET all route');
    return this.http.get<User[]>(`${this.baseURL}`);
  }

  updateRegisterUser(registerUser: User, id: number) {
    return this.http.put<User>(`${this.baseURL}/${id}`, registerUser);
  }

  deleteRegistered(id: number) {
    return this.http.delete<User>(`${this.baseURL}/${id}`);
  }

  getRegisteredUserId(id: number) {
    return this.http.get<User>(`${this.baseURL}/${id}`);
  }
}
