import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
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

  foods: Catering[] = [];


  constructor(
    private router: Router,
    private destinationService: DestinationService,
    // private destination: Destination,
    private userService: UserService
  ) { }

  // ngOnInit(): void {
  //   this.destinationService.getCaterings(this.destinationService.getDestinationId()).subscribe((response) => {
  //     this.foods = response.content;
  //   })
  // }
}
