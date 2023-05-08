import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { each } from 'chart.js/dist/helpers/helpers.core';
import { Observable } from 'rxjs';
import { Purchase } from 'src/app/models/purchase.model';
import { Review } from 'src/app/models/review.model';
import { User } from 'src/app/models/user.model';
import { AuthService } from 'src/app/services/auth.service';
import { DestinationService } from 'src/app/services/destination.service';
import { ReviewService } from 'src/app/services/review.service';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {
  profileAvatarUrl!: string
  user: User | undefined
  reviews: Review[] = [];
  name!: string;
  lastName!: string;
  avatarFile!: File;
  isAdmin: boolean = false;
  numReviewsToShow: number = 3;
  showMoreButton = true;
  purchases: Purchase[] = [];
  
  numPurchaseToShow:number = 3;
  showMorePurchaseButton = true;

  

  constructor(private authService: AuthService, private httpClient: HttpClient,
    private router: Router, private userService: UserService, public activatedRoute: ActivatedRoute, private destinationService: DestinationService) { }

  ngOnInit(): void {
    this.userService.getMe().subscribe((response) => {
      this.user = response;
      this.userService.checkAdmin(this.user).subscribe((response) => {
        this.isAdmin = response;
      }
      )

      this.userService.getProfileAvatar(this.user.id)
        .subscribe(blob => {
          const objectUrl = URL.createObjectURL(blob);
          this.profileAvatarUrl = objectUrl;
        });

      this.userService.getUserReviews(this.user.id)
        .subscribe(reviews => {
          this.reviews = reviews;
        });
      this.destinationService.getUserPurchase(this.user.id)
        .subscribe(purchases => {
          this.purchases = purchases;
        });
    },
      (error) => {
        this.router.navigate(['/error']);
      })
  }



  onFileSelected(event: any) {
    if (event.target.files && event.target.files.length > 0) {
      this.avatarFile = event.target.files[0];
    }
  }

  showMoreReviews() {
    this.numReviewsToShow += 3;
    console.log(this.reviews.length)
    this.showMoreButton = this.numReviewsToShow < this.reviews.length;
  }

  showMorePurchases() {
    this.numPurchaseToShow += 3;
    this.showMorePurchaseButton = this.numPurchaseToShow < this.purchases.length;
  }

  editUser() {
    if (this.user) {
      if (!this.name) {
        console.error('Name needed');
        return;
      }
      this.user.name = this.name;
      this.user.lastName = this.lastName;
      this.userService.editUser(this.user, this.avatarFile).subscribe(
        (_) => {
          console.log(this.user);
          this.ngOnInit();
        },
      );
    }
  }

  deleteUser() {
    if (this.user) {
      this.userService.deleteUser(this.user)
      this.router.navigate(['/']);
    }
  }

  deletPurchase(id: number) {
    this.destinationService.deletePurchase(id).subscribe(() => {
      console.log("Compra eliminada correctamente");
    });
    this.ngOnInit();
  }

  deleteReview(review: Review) {
    return this.httpClient.delete('/api/destinations/reviews/' + review.id).subscribe(() =>
      this.ngOnInit()
    )
  };

  logOut() {
    this.authService.logOut();
    this.router.navigate(['/']);
  }

}



