import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Review } from '../models/review.model';

@Injectable({
  providedIn: 'root'
})



export class ReviewService {

  constructor(private httpClient: HttpClient) { }
  getUserReviews(userId: number): Observable<Review[]> {
    return this.httpClient.get<Review[]>('/api/users/reviews/' + userId);
  }
}
