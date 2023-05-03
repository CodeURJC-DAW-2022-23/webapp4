import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { catchError, map } from 'rxjs/operators';


const BASE_URL = '/api/auth/';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  logged: boolean | undefined
  
  constructor(private httpClient:HttpClient) { }


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

  login(email:string,encodedPassword:string){
    
  }

  logOut() {

    return this.httpClient.post(BASE_URL + '/logout', { withCredentials: true })
        .subscribe((resp: any) => {
            this.logged = false;
        });

  }

  isLogged(){
  }

  currentUser(){
  }




}
