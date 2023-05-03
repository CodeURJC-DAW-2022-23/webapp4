import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { User } from 'src/app/models/user.model';
import { AuthService } from 'src/app/services/auth.service';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent {
  user!: User;

  constructor(private authService: AuthService,
    private router: Router,private userService:UserService, public activatedRoute: ActivatedRoute) { }

  ngOnInit(){
    this.userService.getMe().subscribe((response) => {
      this.user = response;
    });
  }

  getProfileImage() {
    return this.userService.getProfileImage(this.user);
  }

}

