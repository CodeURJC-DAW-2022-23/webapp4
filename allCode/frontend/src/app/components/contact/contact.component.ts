import { Component } from '@angular/core';
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {HttpClient} from "@angular/common/http";

@Component({
  selector: 'app-contact',
  templateUrl: './contact.component.html',
  styleUrls: ['./contact.component.css']
})
export class ContactComponent {
  //title = 'enviodeCorreos';
  datos: FormGroup;
  constructor(private httpclien:HttpClient) {
    this.datos = new FormGroup({
      nombre: new FormControl('',[Validators.required]),
      email: new FormControl('',[Validators.required, Validators.email]),
      subject: new FormControl('',[Validators.required]),
      message: new FormControl('',[Validators.required])
    })
  }
  sendEmail(){
    let params = {
      nombre:this.datos.value.nombre,
      email:this.datos.value.email,
      subject:this.datos.value.subject,
      message:this.datos.value.message
    }
    this.httpclien.post('/api/contact/', params)
      .subscribe(resp => {
        console.log(resp);
      }, err => {
        console.log(err);
      });
  }
}
