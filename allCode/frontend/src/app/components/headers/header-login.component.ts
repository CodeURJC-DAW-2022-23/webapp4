import { Component, NgModule } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'headerLogin',
  templateUrl: './header-login.component.html',
  styleUrls: ['./style-login.component.css', './style-starter.component.css'],
})
export class HeaderLoginComponent {
  public isMenuCollapsed = true;
  public scrolled = false;

  constructor(
    private router:Router,
    activatedRoute: ActivatedRoute
  ){}

  index() {
    this.router.navigate(['/index']);
  }
  destination(){
    this.router.navigate(['/services'])
  }
}
