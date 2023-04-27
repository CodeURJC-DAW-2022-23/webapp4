import { DestinationService } from 'src/app/services/destination.service'

import { Component, OnInit} from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { ActivatedRoute, Router } from '@angular/router'
import { Destination } from 'src/app/models/destination.model';
import { UserService } from 'src/app/services/user.service';
import { User } from 'src/app/models/user.model';

@Component({
  selector: 'app-destination',
  templateUrl: './destination.component.html',
  styleUrls: ['./destination.component.css']
})
export class DestinationComponent implements OnInit{

  destinations: Destination[] = [];
  currentUser: User["id"] | undefined;



  constructor(
    private router: Router,
    private destinationService: DestinationService,
    private userService: UserService
    ) {}

  ngOnInit():void {
    this.destinationService.getDestinations().subscribe((response) => {
      this.destinations = response.content;
    });
    this.userService.getMe().subscribe((response) => {
      this.currentUser = response.id;
    })
  }

  destinationImage(destination: Destination){
    return this.destinationService.getImageDestination(destination);
  }

  goToDestination(id: number){
    this.router.navigate(['/services/'+id])
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
