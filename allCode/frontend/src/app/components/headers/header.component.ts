import { ChangeDetectionStrategy, Component, DoCheck } from '@angular/core';
import { NgbNavModule } from '@ng-bootstrap/ng-bootstrap';
import { DestinationService } from 'src/app/services/destination.service';
import { DestinationComponent } from '../destination/destination.component';

@Component({
	selector: 'headerLogin',
	templateUrl: './header.component.html',
	changeDetection: ChangeDetectionStrategy.OnPush,
	styleUrls: ['./style-starter.component.css','./style-login.component.css']
})
export class HeaderLogin{
	
}