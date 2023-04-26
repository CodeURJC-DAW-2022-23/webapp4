import { DestinationService } from 'src/app/services/destination.service'

import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { ActivatedRoute, Router } from '@angular/router'
import { Destination } from 'src/app/models/destination.model';

@Component({
  selector: 'app-destination',
  templateUrl: './destination.component.html',
  styleUrls: ['./destination.component.css']
})
export class DestinationComponent {

  destinations: Destination[] = [];
  loadImage() {

  }


  constructor(private httpClient: HttpClient,
    private router: Router,
    private destinationService: DestinationService) { 
      this.ngOnInit;
    }

  ngOnInit() {
    this.destinations = []
    this.destinationService.getDestinations().subscribe(
      response => {
        let data: any = response;
        for (var i = 0; i < data.content.length; i++) {
          let nameDestination = data.content[i].nameDestination
          this.destinations.push(nameDestination)
        }

      })
  }









  // search() {
  //   this.destinations = [];
  //   let url = "/api/destinations/";
  //   this.httpClient.get(url).subscribe(
  //     response => {
  //       let data: any = response;
  //       console.log(response)
  //     },
  //     error => console.error(error)
  //   )
  // }

}
