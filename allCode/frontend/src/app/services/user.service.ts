import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { User } from '../models/user.model';
import { Observable } from 'rxjs';

const baseUrl = '/api/users/';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private httpClient: HttpClient) { }

  getMe():Observable<User>{
    return this.httpClient.get<User>(baseUrl+"me")
  }
}
