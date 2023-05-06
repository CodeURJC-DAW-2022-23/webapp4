import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { Review } from 'src/app/models/review.model';
import { User } from 'src/app/models/user.model';
import { AuthService } from 'src/app/services/auth.service';
import { ReviewService } from 'src/app/services/review.service';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-administrator',
  templateUrl: './administrator.component.html',
  styleUrls: ['./administrator.component.css']
})
export class AdministratorComponent {
  user!: User;
  profileAvatarUrls: string[] = [];
  reviews!: Review[];
  isAdmin: boolean | undefined
  userList: User[] = [];

  name: string[] = [];
  lastName: string[] = [];
  avatarFile: File[] = [];

  constructor(private authService: AuthService, private httpClient: HttpClient,
    private router: Router, private userService: UserService, public activatedRoute: ActivatedRoute, private reviewService: ReviewService) { }




  ngOnInit(): void {
    this.userService.getMe().subscribe(response => {
      this.user = response;
      this.userService.checkAdmin(this.user).subscribe(isAdmin => {
        this.isAdmin = isAdmin;

        if (this.isAdmin) {
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

  editUser(user: User) {
    if (user) {
      if (!user.name) {
        console.error('Name needed');
        return;
      }
      user.name = this.name[user.id];
      user.lastName = this.lastName[user.id];
      this.userService.editUser(user, this.avatarFile[user.id]).subscribe(
        (_) => {
          console.log(user);
          this.ngOnInit();
        },
      );
    }
  }

  deleteUser(user: User) {
    if (user) {
      this.userService.deleteUser(user)
      this.ngOnInit();
    }

  }
}
