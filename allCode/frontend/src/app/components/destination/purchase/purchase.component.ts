import { Component } from '@angular/core';
import {Destination} from "../../../models/destination.model";
import {ActivatedRoute, Router} from "@angular/router";
import {DestinationService} from "../../../services/destination.service";
import {UserService} from "../../../services/user.service";
import {filter} from "rxjs";
import {House} from "../../../models/house.model";
import {HttpClient} from "@angular/common/http";

@Component({
  selector: 'app-purchase',
  templateUrl: './purchase.component.html',
  styleUrls: ['./purchase.component.css']
})
export class PurchaseComponent {
  //nameDestination = '';
  currentDestination: number;
  currentHouse: number;
  house: House |undefined;
  destination: Destination | undefined;
  actual: Destination | undefined;

  constructor(
    private router: Router,
    private httpClient: HttpClient,
    private destinationService: DestinationService,
    private userService: UserService,
    private activatedRoute: ActivatedRoute
  ) {
    this.currentDestination = this.activatedRoute.snapshot.params['destinationId'];
    this.currentHouse = this.activatedRoute.snapshot.params['id'];
    console.log(this.currentDestination);
    console.log(this.currentHouse);
    let dest = destinationService.getDestinationById(this.currentDestination)
      .pipe(filter(dest => dest !== undefined))
      .subscribe(dest => {
        this.destination = dest;
      }, error => {
        throw new Error('Unknown destination')
      });
    let house = destinationService.getHouse2(this.currentHouse)
      .pipe(filter(house => house !== undefined))
      .subscribe(house => {
        this.house = house;
        console.log(this.house);
      }, error => {
        throw new Error('Unknown house')
      });
  }

  ngOnInit(){
    this.destinationService.getDestinationById(this.currentDestination).subscribe((response) => {
      this.actual = response;
    })
  }
  addPurchase() {
    const purchaseData = {
      // Aquí va la información de la compra
    };
    this.httpClient.post('/api/purchases/' + this.currentHouse, purchaseData).subscribe(
      response => {
        this.router.navigate(['/'])
        console.log('Compra agregada exitosamente');
      },
      error => {
        console.error('Error al agregar la compra', error);
      }
    );
  }



}
