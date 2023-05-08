import { Component } from '@angular/core';
import { AuthService } from '../../services/auth.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-register',
  styleUrls: ['./register.component.css'],
  templateUrl: './register.component.html',
})
export class RegisterComponent {
  name: string = '';
  lastName: string = '';
  email: string = '';
  password: string = '';

  constructor(public authService: AuthService, private router: Router) {

  }

  onRegister() {
    const userData = {
      name: this.name,
      lastName: this.lastName,
      email: this.email,
      passwordEncoded: this.password,
    };
    this.authService.register(userData).subscribe(
      (_) => {
        this.router.navigate(['/login']);
      },
      (_) => {
        console.error("error");
      }
    );
  }

}


