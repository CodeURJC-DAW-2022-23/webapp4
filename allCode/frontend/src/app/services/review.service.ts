import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Review } from '../models/review.model';

const baseUrl = '/api/reviews';
@Injectable({
  providedIn: 'root'
})

export class ReviewService {
  constructor(private httpClient: HttpClient) { }
 

  getAllReviews(): Observable<Review[]> {
    return this.httpClient.get<Review[]>('/api/destinations/reviews');
  }
}
