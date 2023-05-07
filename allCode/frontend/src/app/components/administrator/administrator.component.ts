import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { Destination } from 'src/app/models/destination.model';
import { Review } from 'src/app/models/review.model';
import { User } from 'src/app/models/user.model';
import { AuthService } from 'src/app/services/auth.service';
import { DestinationService } from 'src/app/services/destination.service';
import { ReviewService } from 'src/app/services/review.service';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-administrator',
  templateUrl: './administrator.component.html',
  styleUrls: ['./administrator.component.css']
})
export class AdministratorComponent {

  user: User | undefined;

  profileAvatarUrls: string[] = [];
  reviews!: Review[];
  isAdmin: boolean | undefined

  userList: User[] = [];
  reviewList: Review[] = [];
  destinations: Destination[] = [];

  name: string[] = [];
  lastName: string[] = [];
  avatarFile: File[] = [];

  destinationName: string[] = [];
  destinationContent: string[] = [];
  destinationImageFile: File[] = [];
  destinationPrice: number[] = [];
  destinationSize: number = 0;

  numReviewsToShow: number = 3;
  showMoreButton = true;

  constructor(private destinationService: DestinationService, private httpClient: HttpClient,
    private router: Router, private userService: UserService, public activatedRoute: ActivatedRoute, private reviewService: ReviewService) { }


  ngOnInit(): void {

    this.userService.getMe().subscribe(response => {
      this.user = response;
      this.userService.checkAdmin(this.user).subscribe(isAdmin => {
        this.isAdmin = isAdmin;

        if (this.isAdmin) {
          this.destinationService.getDestinations().subscribe((response) => {
            this.destinations = response.content;
          });

          this.userService.getUserList().subscribe(users => {
            this.userList = users;
            this.userList.forEach(user => {
              this.userService.getProfileAvatar(user.id).subscribe(blob => {
                const objectUrl = URL.createObjectURL(blob);
                this.profileAvatarUrls[user.id] = objectUrl;
              });
            });
          });
          this.reviewService.getAllReviews().subscribe(reviews => {
            this.reviewList = reviews;
          })
        }
      });
    }
    );
  }

  showMoreReviews() {
    this.numReviewsToShow += 3;
    this.showMoreButton = this.numReviewsToShow < this.reviews.length;
  }

  onFileSelected(event: any, user: User) {
    if (event.target.files && event.target.files.length > 0) {
      this.avatarFile[user.id] = event.target.files[0];
    }
  }

  onDestinationFileSelected(event: any, destination: Destination) {
    if (event.target.files && event.target.files.length > 0) {
      this.destinationImageFile[destination.id] = event.target.files[0];
    }
  }

  onNewDestinationFileSelected(event: any) {
    if (event.target.files && event.target.files.length > 0) {
      this.destinationImageFile[this.destinations.length] = event.target.files[0];
    }
  }

  destinationImage(destination: Destination) { 
    return this.destinationService.getImageDestination(destination);
  }

  addDestination() {
    const formData = new FormData();
    formData.append('destinationName', this.destinationName[this.destinations.length] || '');
    formData.append('destinationContent', this.destinationContent[this.destinations.length] || '');
    formData.append('price', this.destinationPrice[this.destinations.length].toString())
    formData.append('destinationImageFile', this.destinationImageFile[this.destinations.length]);
    console.log(this.destinationImageFile[this.destinations.length])
    this.destinationService.addDestination(formData).subscribe(
      (response) => {
        console.log(response);
        this.ngOnInit();
        alert("destination added");

      },
      (error) => {
        console.log(error);
        this.ngOnInit();
        alert("Error destination");
      }
    );
  }


  editDestination(destination: Destination) {
    if (destination) {

      destination.nameDestination = this.destinationName[destination.id];
      destination.contentDestination = this.destinationContent[destination.id];
      destination.price = this.destinationPrice[destination.id]

      if (!destination.nameDestination) {
        console.error('NameDestination needed');
        return;
      }

      this.destinationService.editDestination(destination, this.destinationImageFile[destination.id]).subscribe(
        (response) => {
          console.log(response);
          this.ngOnInit();
          alert("Destination updated successfully");

        },
        (error) => {
          console.log(error);
          this.ngOnInit();
          alert("Error updating destination");

        }
      );
    }
  }



  editUser(user: User) {
    if (user) {
      if (!user.name) {
        console.error('Name needed');
        return;
      }
      user.name = this.name[user.id];
      user.lastName = this.lastName[user.id];
      this.userService.editUser(user, this.avatarFile[user.id]).subscribe(
        (response) => {
          console.log(response);
          this.ngOnInit();
          alert("User updated successfully");

        },
        (error) => {
          console.log(error);
          this.ngOnInit();
          alert("Error updating user");
        }
      );
    }
  }

  deleteUser(user: User) {
    return this.httpClient.delete('/api/users/' + user.id).subscribe(() =>
      this.ngOnInit()
    );
  }

  deleteDestination(destination: Destination) {
    return this.httpClient.delete('/api/destinations/' + destination.id).subscribe(() =>
      this.ngOnInit()
    )
  };

  deleteReview(review: Review) {
    return this.httpClient.delete('/api/destinations/reviews/' + review.id).subscribe(() =>
      this.ngOnInit()
    )
  };
}

