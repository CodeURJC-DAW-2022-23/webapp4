import { ChangeDetectionStrategy, Component, DoCheck } from '@angular/core';
import { NgbNavModule } from '@ng-bootstrap/ng-bootstrap';

@Component({
	selector: 'headerLogin',
	templateUrl: './header.component.html',
	changeDetection: ChangeDetectionStrategy.OnPush,
	styleUrls: ['./style-starter.component.css','./style-login.component.css']
})
export class HeaderLogin{

}