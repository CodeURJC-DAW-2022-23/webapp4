import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { filter } from 'rxjs';
import { Catering } from 'src/app/models/catering.model';
import { Destination } from 'src/app/models/destination.model';
import { DestinationService } from 'src/app/services/destination.service';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-catering',
  templateUrl: './catering.component.html',
  styleUrls: ['../tourism-catering.component.css']
})
export class CateringComponent  {
  nameDestination = '';
  currentDestination: number;
  destination: Destination | undefined ;
  foods: Catering[] = [];


  constructor(
    private destinationService: DestinationService,
    private activatedRoute: ActivatedRoute,
  ) {
    this.currentDestination = activatedRoute.snapshot.params['id'];
    let dest = destinationService.getDestinationById(this.currentDestination)
    .pipe(filter(dest => dest !== undefined))
    .subscribe(dest => {
      this.destination = dest;
      this.nameDestination = this.destination?.nameDestination ?? 'UD';
    }, error => {
      throw new Error('Unknown destination')
    });
  }

  ngOnInit():void {
    
    this.destinationService.getCaterings(this.currentDestination).subscribe((response) => {
      this.foods = response.content;
    });
  }
  foodImage(id: number){
    return this.destinationService.getImageFood(id);
  }
}
