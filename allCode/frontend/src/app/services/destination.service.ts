import { Injectable } from '@angular/core';
import { Observable, catchError, of, throwError } from 'rxjs';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Destination } from '../models/destination.model';
import { Page } from '../models/page.model';
import { Catering } from '../models/catering.model';
import { Tourism } from '../models/tourism.model';
import { House } from '../models/house.model';
import { Review } from "../models/review.model";
import { Purchase } from '../models/purchase.model';

const baseUrl = '/api/destinations/';

@Injectable({
  providedIn: 'root'
})
export class DestinationService {





  constructor(private httpClient: HttpClient) { }

  getDestinations(): Observable<Page<Destination>> {
    return this.httpClient.get<Page<Destination>>(baseUrl)
  }

  getCaterings(id: number): Observable<Page<Catering>> {
    return this.httpClient.get<Page<Catering>>(baseUrl + 'catering/' + id)
  }

  getDestinationReviews(destination: number): Observable<Review[]> {
    return this.httpClient.get<Review[]>(baseUrl + 'reviews/' + destination);
  }


  getTourism(id: number): Observable<Page<Tourism>> {
    return this.httpClient.get<Page<Tourism>>(baseUrl + 'tourism/' + id)
  }

  getHouse(id: number): Observable<any> {
    return this.httpClient.get<any>(baseUrl + 'house/' + id)
  }

  getHouse2(id: number): Observable<any> {
    return this.httpClient.get<any>(baseUrl + 'houses/' + id)
  }

  getDestinationById(id: number): Observable<Destination> {
    return this.httpClient.get<Destination>(baseUrl + id)
  }

  getImageDestination(destination: Destination) {
    return baseUrl + destination.id + '/image'
  }

  getImageFood(id: number) {
    return baseUrl + 'catering/' + id + '/image'
  }

  getImagePlace(id: number) {
    return baseUrl + 'tourism/' + id + '/image'
  }

  getImageHouse(id: number) {
    return baseUrl + 'house/' + id + '/image'
  }

  editDestination(destination: Destination, destinationImageFile?: File): Observable<any> {
    const formData = new FormData();
    formData.append('destinationName', destination.nameDestination || '');
    formData.append('destinationContent', destination.contentDestination || '');
    formData.append('price', (destination.price.toString()));
    if (destinationImageFile) {
      formData.append('destinationImageFile', destinationImageFile);
    }

    return this.httpClient.post(baseUrl + "editDestination/" + destination.id, formData).pipe(
      catchError((error) => {
        return throwError(error);
      })
    );
  }

  addDestination(formData: FormData) {
    return this.httpClient.post(baseUrl + "add", formData)
  }

  getUserPurchase(id: number): Observable<Purchase[]> {
    return this.httpClient.get<Purchase[]>("/api/purchases/user/" + id)
  }

  getAllPurcahse(): Observable<Purchase[]> {
    return this.httpClient.get<Purchase[]>("/api/purchases/all")
  }


  getNameHouse(id: number): Observable<String> {
    return this.httpClient.get<String>("/api/purchases/" + id);
  }

  deletePurchase(id: number) {
    return this.httpClient.delete("/api/purchases/" + id)
  }
}
