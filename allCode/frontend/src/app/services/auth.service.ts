import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { catchError, map } from 'rxjs/operators';
import { User } from '../models/user.model';


const BASE_URL = '/api/auth/';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  logged: boolean | undefined;
  user: User | undefined;

  
  constructor(private httpClient:HttpClient) { }

  userLogged(){
    this.httpClient.get('/api/users/me', { withCredentials: true }).subscribe(
      response => {
          this.user = response as User;
          this.logged = true;
          return this.user
      },
      _ => {
            throw new Error('User not exist');
      }
  );
  }

  register(userData:any): Observable<any> {
    return this.httpClient.post("/api/users/", userData)
      .pipe(
        map((response:any) => {
          return response;
        }),
        catchError((error: any) => {
          return throwError('Register Error');
        })
      );
  }

  login(email: string, password: string): Observable<any> {
    return this.httpClient.post(BASE_URL + "/login", { username: email, password: password }, { withCredentials: true })
      .pipe(
        map((response: any) => { 
        }),
        catchError((error: any) => {
          return throwError('Login Error');
        })
      );
  }

  logOut() {
    return this.httpClient.post(BASE_URL + '/logout', { withCredentials: true })
        .subscribe(() => {
            this.logged = false;
            this.user = undefined;
        });
  }

  isLogged(){
  }

  currentUser(){
  }




}
