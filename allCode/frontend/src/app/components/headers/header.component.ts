import { ChangeDetectionStrategy, Component, DoCheck } from '@angular/core';
import { NavigationEnd, Router } from '@angular/router';
import { HttpClient } from '@angular/common/http';
import { User } from "../../models/user.model";
import { AuthService } from "../../services/auth.service";
import { UserService } from "../../services/user.service";

@Component({
  selector: 'headerLogin',
  templateUrl: './header.component.html',
  styleUrls: ['./style-starter.component.css', './style-login.component.css']
})
export class HeaderLogin {
  currentUser: User | undefined;
  isAdmin: boolean | undefined;
  loggedIn: boolean = false;

  constructor(private authService: AuthService, private httpClient: HttpClient,
    private router: Router, private userService: UserService) { this.ngOnInit() }

  ngOnInit() {
    this.userService.getMe().subscribe((response) => {
      this.currentUser = response;

      this.authService.isLoggedIn.subscribe((isLoggedIn) => {
        this.loggedIn = isLoggedIn;
      });
      console.log(this.loggedIn)
    })
  }
}
