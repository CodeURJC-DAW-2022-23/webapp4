import { Component, OnInit} from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router'
import { DestinationService } from 'src/app/services/destination.service';

@Component({
  selector: 'app-information',
  templateUrl: './information.component.html',
  styleUrls: ['./information.component.css']
})
export class InformationComponent {
  currentDestination: number | undefined;

  

  constructor(private router: Router,
    activatedRoute: ActivatedRoute,
    destinationService: DestinationService
    ) { 
      let id = activatedRoute.snapshot.params['id'];
      this.currentDestination = id;
    }

  goToCatering() {
    return this.router.navigate(['/catering/' + this.currentDestination])
  }
  goToTourism(){
    return this.router.navigate(['/tourism/' + this.currentDestination])
  }
  goToReviews(){
    return this.router.navigate(['/review/' + this.currentDestination])
  }
  goToHouse(){
    return this.router.navigate(['/house/' + this.currentDestination])
  }
}
