import { ChangeDetectionStrategy, Component, DoCheck } from '@angular/core';
import { NgbNavModule } from '@ng-bootstrap/ng-bootstrap';

@Component({
	selector: 'headerLogin',
	templateUrl: './header-login.component.html',
	changeDetection: ChangeDetectionStrategy.OnPush,

})
export class HeaderLogin implements DoCheck{
	ngDoCheck() {
		console.count('checked');
	  }
}