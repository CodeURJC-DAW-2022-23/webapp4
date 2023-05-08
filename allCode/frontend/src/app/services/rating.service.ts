

import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { Page } from '../models/page.model';

const baseUrl = '/api/destinations/';


@Injectable({
  providedIn: 'root'
})
export class RatingService {

  constructor(private httpClient: HttpClient) { }

  getRatings(): Observable<any> {
    return this.httpClient.get<any>(baseUrl + 'rating/')
  }


}


