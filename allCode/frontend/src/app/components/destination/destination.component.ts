import { Destination } from './../../models/destination';
import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-destination',
  templateUrl: './destination.component.html',
  styleUrls: ['./destination.component.css']
})
export class DestinationComponent {

  destinations:string[]=[];
  constructor(private httpClient:HttpClient){}


  search(){
    // this.destinations=[];
    // let url="/api/destinations/";
    // this.httpClient.get(url).subscribe(
    //   response => {
    //     let data:any=response;
    //     console.log(response)
    //   },
    //   error => console.error(error)
    // )
  }

}
