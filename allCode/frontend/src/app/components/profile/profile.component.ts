import { HttpBackend, HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { User } from 'src/app/models/user.model';
import { AuthService } from 'src/app/services/auth.service';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {
  profileAvatarUrl!: string;
  user: User | undefined
  constructor(private authService: AuthService, private httpClient: HttpClient,
    private router: Router, private userService: UserService, public activatedRoute: ActivatedRoute) { }

  ngOnInit(): void {
    this.userService.getMe().subscribe((response) => {
      this.user = response;
      this.httpClient.get('/api/users/profileAvatarFile/' + this.user.id, { responseType: 'blob' })
        .subscribe(blob => {
          const objectUrl = URL.createObjectURL(blob);
          this.profileAvatarUrl = objectUrl;
        });
    })
  }

  logOut() {
    this.authService.logOut();
    this.router.navigate(['/']);
  }
}



