import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { Destination } from '../models/destination.model';
import { Page } from '../models/page.model';

const baseUrl = '/api/destinations/';

@Injectable({
  providedIn: 'root'
})
export class DestinationService {

  constructor(private httpClient: HttpClient) { }

  getDestinations(): Observable<Page<Destination>>{
    return this.httpClient.get<Page<Destination>>(baseUrl)
  }
 ///  api/destinations/9/image
  getImageDestination(destination: Destination){
    return baseUrl + destination.id+ '/image'
  }
}
