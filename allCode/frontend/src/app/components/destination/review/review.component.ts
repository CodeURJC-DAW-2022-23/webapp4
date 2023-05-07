import { Component } from '@angular/core';
import {User} from "../../../models/user.model";
import {Review} from "../../../models/review.model";
import {Destination} from "../../../models/destination.model";
import {DestinationService} from "../../../services/destination.service";
import {ActivatedRoute} from "@angular/router";
import { ReviewService } from 'src/app/services/review.service';
import {filter} from "rxjs";

@Component({
  selector: 'app-review',
  templateUrl: './review.component.html',
  styleUrls: ['./review.component.css']
})
export class ReviewComponent {
  profileAvatarUrl!: string
  user: User | undefined
  reviews: Review[] = [];
  name!: string;
  lastName!: string;
  avatarFile!: File;
  currentDestination: number;
  destination: Destination | undefined

  numReviewsToShow: number = 3;
  showMoreButton = true;

  constructor(
    private destinationService: DestinationService,
    private activatedRoute: ActivatedRoute,
  ) {
    this.currentDestination = activatedRoute.snapshot.params['id'];
    let dest = destinationService.getDestinationById(this.currentDestination)
      .pipe(filter(dest => dest !== undefined))
      .subscribe(dest => {
        this.destination = dest;
      }, error => {
        throw new Error('Unknown destination')
      });
    this.destinationService.getDestinationReviews(this.currentDestination).subscribe((response) => {
      this.reviews = response;
    });
    console.log(this.reviews);
  }

  showMoreReviews() {
    this.numReviewsToShow += 3;
    this.showMoreButton = this.numReviewsToShow < this.reviews.length;
  }
}
