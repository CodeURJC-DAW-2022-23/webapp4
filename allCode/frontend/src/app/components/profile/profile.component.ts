import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Review } from 'src/app/models/review.model';
import { User } from 'src/app/models/user.model';
import { AuthService } from 'src/app/services/auth.service';
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
  reviews: Review[] | undefined
  name!: string;
  lastName!: string;
  avatarFile!: File;

  constructor(private authService: AuthService, private httpClient: HttpClient,
    private router: Router, private userService: UserService, public activatedRoute: ActivatedRoute, private reviewService: ReviewService) { }

  ngOnInit(): void {
    this.userService.getMe().subscribe((response) => {
      this.user = response;

      this.userService.getProfileAvatar(this.user.id)
        .subscribe(blob => {
          const objectUrl = URL.createObjectURL(blob);
          this.profileAvatarUrl = objectUrl;
        });

      this.userService.getUserReviews(this.user.id)
        .subscribe(reviews => {
          this.reviews = reviews;
        });
      //purchases 
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

  logOut() {
    this.authService.logOut();
    this.router.navigate(['/']);
  }

}



