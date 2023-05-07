import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { filter } from 'rxjs';
import { Destination } from 'src/app/models/destination.model';
import { House } from 'src/app/models/house.model';
import { DestinationService } from 'src/app/services/destination.service';

@Component({
  selector: 'app-house',
  templateUrl: './house.component.html',
  styleUrls: ['./house.component.css']
})
export class HouseComponent implements OnInit {

  nameDestination = '';
  currentDestination: number;
  destination: Destination | undefined;
  house: House | undefined;


  constructor(
    private router: Router,
    private destinationService: DestinationService,
    private activatedRoute: ActivatedRoute,
  ){
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


  ngOnInit(): void {
    this.destinationService.getHouse(this.currentDestination).subscribe((response) => {
      this.house = response[0];
    });
  }

  houseImage(id: number | undefined) {
    if (id === undefined) {
      return 'Error al obtener la imagen';
    }
    else {
    return this.destinationService.getImageHouse(id);
  }};

  goToPurchase(){
    return this.router.navigate(['/purchase/' + this.currentDestination])
  }
}
