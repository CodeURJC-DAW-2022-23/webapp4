import { ChangeDetectionStrategy, Component, DoCheck } from '@angular/core';
import { Router } from '@angular/router';
import { HttpClient } from '@angular/common/http';
import {User} from "../../models/user.model";
import {AuthService} from "../../services/auth.service";
import {UserService} from "../../services/user.service";

@Component({
	selector: 'headerLogin',
	templateUrl: './header.component.html',
	styleUrls: ['./style-starter.component.css', './style-login.component.css']
})
export class HeaderLogin {
  currentUser: User | undefined;
  isAdmin: boolean | undefined;

  constructor(private authService: AuthService, private httpClient: HttpClient,
              private router: Router, private userService: UserService) {}

  ngOnInit(): void {
    this.userService.getMe().subscribe((response) => {
      this.currentUser = response;
      console.log(this.currentUser.name);
      this.userService.checkAdmin(this.currentUser).subscribe(isAdmin => {
        this.isAdmin = isAdmin;
      })
    })
  }

}
