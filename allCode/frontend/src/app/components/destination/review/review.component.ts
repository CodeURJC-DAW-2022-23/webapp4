import { Component } from '@angular/core';
import {User} from "../../../models/user.model";
import {Review} from "../../../models/review.model";
import {Destination} from "../../../models/destination.model";
import {DestinationService} from "../../../services/destination.service";
import {ActivatedRoute} from "@angular/router";
import { ReviewService } from 'src/app/services/review.service';
import {filter} from "rxjs";
import {UserService} from "../../../services/user.service";
import {HttpClient} from "@angular/common/http";

@Component({
  selector: 'app-review',
  templateUrl: './review.component.html',
  styleUrls: ['./review.component.css']
})
export class ReviewComponent {
  profileAvatarUrl!: string
  user: User | undefined
  reviews: Review[] = [];
  content!: string;
  title!: string;
  rating!: number;
  currentDestination: number;
  destination: Destination | undefined

  numReviewsToShow: number = 3;
  showMoreButton = true;

  constructor(
    private destinationService: DestinationService,
    private userService: UserService,
    private activatedRoute: ActivatedRoute,
    private httpClient: HttpClient
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
      console.log(this.reviews[1].user);
    });
  }
  showMoreReviews() {
    this.numReviewsToShow += 3;
    this.showMoreButton = this.numReviewsToShow < this.reviews.length;
  }
  addReview() {
    const formData = new FormData();
    formData.append('reviewTitle', this.title || '');
    formData.append('reviewContent', this.content || '');
    formData.append('rating', this.rating.toString())
    this.httpClient.post('/api/destinations/reviews/' + this.currentDestination, formData)
    .subscribe(
      (response) => {
        console.log(response);
        alert("destination added");
      },
      (error) => {
        console.log(error);
        alert("Error destination");
      }
    );
  }
}
