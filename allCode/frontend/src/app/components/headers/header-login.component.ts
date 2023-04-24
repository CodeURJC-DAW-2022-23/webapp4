import { Component } from '@angular/core';
import { NgbNavModule } from '@ng-bootstrap/ng-bootstrap';

@Component({
	selector: 'headerLogin',
	standalone: true,
	imports: [NgbNavModule],
	templateUrl: './header-login.component.html',
})
export class NgbdNavBasic {
	active = 1;
}