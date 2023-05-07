import { Component } from '@angular/core';
import {Destination} from "../../../models/destination.model";
import {ActivatedRoute, Router} from "@angular/router";
import {DestinationService} from "../../../services/destination.service";
import {UserService} from "../../../services/user.service";
import {filter} from "rxjs";

@Component({
  selector: 'app-purchase',
  templateUrl: './purchase.component.html',
  styleUrls: ['./purchase.component.css']
})
export class PurchaseComponent {
  //nameDestination = '';
  currentDestination: number;
  destination: Destination | undefined;
  actual: Destination | undefined;

  constructor(
    private router: Router,
    private destinationService: DestinationService,
    private userService: UserService,
    private activatedRoute: ActivatedRoute
  ) {
    this.currentDestination = this.activatedRoute.snapshot.params['id'];
    let dest = destinationService.getDestinationById(this.currentDestination)
      .pipe(filter(dest => dest !== undefined))
      .subscribe(dest => {
        this.destination = dest;
      }, error => {
        throw new Error('Unknown destination')
      });
  }

  ngOnInit(){
    this.destinationService.getDestinationById(this.currentDestination).subscribe((response) => {
      this.actual = response;
    })
  }

}
