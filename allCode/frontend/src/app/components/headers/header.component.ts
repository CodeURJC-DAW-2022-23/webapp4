import { ChangeDetectionStrategy, Component, DoCheck } from '@angular/core';
import { Router } from '@angular/router';
import { HttpClient } from '@angular/common/http';

@Component({
	selector: 'headerLogin',
	templateUrl: './header.component.html',
	changeDetection: ChangeDetectionStrategy.OnPush,
  styleUrls: ['../../../styles.css', './style-login.component.css']
})
export class HeaderLogin {
	constructor(private router: Router, private httpClient: HttpClient) {}

}
