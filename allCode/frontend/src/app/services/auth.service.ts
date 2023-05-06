import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { catchError, map } from 'rxjs/operators';
import { User } from '../models/user.model';
import { UserService } from './user.service';


const BASE_URL = '/api/auth/';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  logged: boolean | undefined;
  user:User| undefined;

 constructor(private httpClient: HttpClient, private userService: UserService) {
    this.reqIsLogged();
}

reqIsLogged() {
    this.httpClient.get('/api/users/me', { withCredentials: true }).subscribe(
        response => {
            this.user = response as User;
            this.logged = true;
        },
        _ => {
              throw new Error('Something bad happened');
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

logIn(user: string, pass: string): Observable<any> {
  return this.httpClient.post(BASE_URL + "login", { username: user, password: pass }, { withCredentials: true })
    .pipe(
      map((response: any) => {
        this.reqIsLogged();
        return response;
      }),
      catchError((error: any) => {
        return throwError('user not exist');
      })
    );
}

logOut() {
    return this.httpClient.post(BASE_URL + 'logout', { withCredentials: true })
        .subscribe((response: any) => {
            this.logged = false;
            this.user = undefined;
        });
}

}
