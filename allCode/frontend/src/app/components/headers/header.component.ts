import { ChangeDetectionStrategy, Component, DoCheck } from '@angular/core';
import { NgbNavModule } from '@ng-bootstrap/ng-bootstrap';
import { DestinationService } from 'src/app/services/destination.service';
import { DestinationComponent } from '../destination/destination.component';
import { Router } from '@angular/router';
import { User } from 'src/app/models/user.model';
import { AuthService } from 'src/app/services/auth.service';
import { HttpClient } from '@angular/common/http';

@Component({
	selector: 'headerLogin',
	templateUrl: './header.component.html',
	changeDetection: ChangeDetectionStrategy.OnPush,
	styleUrls: ['./style-starter.component.css','./style-login.component.css']
})
export class HeaderLogin{

	constructor(private router: Router, private httpClient:HttpClient) { }

	user: User | undefined
	
	userLogged(){
		this.httpClient.get('/api/users/me', { withCredentials: true }).subscribe(
		  response => {
			  this.user = response as User;
			  console.log('User:', this.user);
		  },
		  _ => {
				throw new Error('Something bad happened');
		  }
	  );
	  }
	
}