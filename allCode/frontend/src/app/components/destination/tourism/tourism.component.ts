import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { filter } from 'rxjs';
import { Destination } from 'src/app/models/destination.model';
import { Tourism } from 'src/app/models/tourism.model';
import { DestinationService } from 'src/app/services/destination.service';


@Component({
  selector: 'app-tourism',
  templateUrl: './tourism.component.html',
  styleUrls: ['../tourism-catering.component.css']
})
export class TourismComponent implements OnInit {
  nameDestination = '';
  currentDestination: number;
  destination: Destination | undefined;
  tourisms: Tourism[] = [];

  constructor(
    private destinationService: DestinationService,
    private activatedRoute: ActivatedRoute,
  ) {
    this.currentDestination = activatedRoute.snapshot.params['id'];
    let dest = destinationService.getDestinationById(this.currentDestination)
      .pipe(filter(dest => dest !== undefined))
      .subscribe(dest => {
        this.destination = dest;
        this.nameDestination = this.destination?.nameDestination ?? 'Unknown Destination';
      }, error => {
        throw new Error('Unknown destination')
      });
  }

  ngOnInit():void{
    this.destinationService.getTourism(this.currentDestination).subscribe((response) => {
      this.tourisms = response.content; 
    })
  }

  tourismImage(id: number){
    return this.destinationService.getImagePlace(id); 
  }
}
