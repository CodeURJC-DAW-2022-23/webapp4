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
  destinations: Destination[] = [];
  profileAvatarUrls: string[] = [];
  reviews!: Review[];
  isAdmin: boolean | undefined
  userList: User[] = [];

  name: string[] = [];
  lastName: string[] = [];
  avatarFile: File[] = [];

  destinationName: string[] = [];
  destinationContent: string[] = [];
  destinationImageFile: File[] = [];
  destinationPrice: number[] = [];

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
        }
      });
    });
  }

  onFileSelected(event: any, user: User) {
    if (event.target.files && event.target.files.length > 0) {
      this.avatarFile[user.id] = event.target.files[0];
    }
  }

  onDestinationFileSelected(event: any, destination: Destination) {
    if (event.target.files && event.target.files.length > 0) {
      this.destinationImageFile[destination.id] = event.target.files[0];
      console.log(this.destinationImageFile)
    }
  }

  destinationImage(destination: Destination) {
    return this.destinationService.getImageDestination(destination);
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
          alert("Destination updated successfully");
          this.ngOnInit();
        },
        (error) => {
          console.log(error);
          alert("Error updating destination");
          this.ngOnInit();
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
          alert("User updated successfully");
          this.ngOnInit();
        },
        (error) => {
          console.log(error);
          alert("Error updating user");
        }
      );
    }
  }

  deleteUser(user: User) {
    return this.httpClient.delete('/api/users/' + user.id).subscribe(() => {
      this.ngOnInit();
    });
  }

  deleteDestination(destination: Destination) {
    return this.httpClient.delete('/api/destinations/' + destination.id).subscribe(() => this.ngOnInit()
    )
  };
}

