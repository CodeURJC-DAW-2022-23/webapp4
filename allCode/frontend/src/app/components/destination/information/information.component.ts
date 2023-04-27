import { Component, OnInit} from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router'
import { Destination } from 'src/app/models/destination.model';
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
}
