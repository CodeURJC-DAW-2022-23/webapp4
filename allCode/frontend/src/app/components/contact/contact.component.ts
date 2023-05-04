import { ContactService } from 'src/app/services/destination.service'

import { Component, OnInit} from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router'
import { Destination } from 'src/app/models/contact.model';
import { UserService } from 'src/app/services/user.service';
import { User } from 'src/app/models/user.model';
import { PaginationInstance } from 'ngx-pagination';

@Component({
  selector: 'app-contact',
  templateUrl: './contact.component.html',
  styleUrls: ['./contact.component.css']
})
export class ContactComponent implements OnInit{


}
