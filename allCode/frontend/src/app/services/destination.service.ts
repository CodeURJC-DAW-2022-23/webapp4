import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { Destination } from '../models/destination.model';
import { Page } from '../models/page.model';
import { Catering } from '../models/catering.model';

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
 ///  api/destinations/9/image
  getImageDestination(destination: Destination){
    return baseUrl + destination.id+ '/image'
  }

  getImageFood(destination: Destination){
    return baseUrl + 'catering/'+destination.id+'/image'
  }

  getImagePlace(destination: Destination){
        return baseUrl + 'tourism/'+destination.id+'/image'
  }

}
