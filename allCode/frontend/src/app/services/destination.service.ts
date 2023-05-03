import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { Destination } from '../models/destination.model';
import { Page } from '../models/page.model';
import { Catering } from '../models/catering.model';
import { Tourism } from '../models/tourism.model';

const baseUrl = '/api/destinations/';

@Injectable({
  providedIn: 'root'
})
export class DestinationService {

  constructor(private httpClient: HttpClient) { }

  getDestinations(): Observable<Page<Destination>>{
    return this.httpClient.get<Page<Destination>>(baseUrl)
  }

  getCaterings(id: number): Observable<Page<Catering>>{
    return this.httpClient.get<Page<Catering>>(baseUrl+'catering/'+ id)
  }
  
  getTourism(id: number): Observable<Page<Tourism>>{
    return this.httpClient.get<Page<Tourism>>(baseUrl+'tourism/'+ id)
  }

  getDestinationById(id: number): Observable<Destination>{
    return this.httpClient.get<Destination>(baseUrl+id)
  }

  getImageDestination(destination: Destination){
    return baseUrl + destination.id+ '/image'
  }

  getImageFood(id: number){
    return baseUrl + 'catering/'+id+'/image'
  }

  getImagePlace(id: number){
        return baseUrl + 'tourism/'+id+'/image'
  }

}
