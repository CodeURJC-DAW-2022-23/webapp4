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
      correo: new FormControl('',[Validators.required, Validators.email]),
      nombre: new FormControl('',[Validators.required]),
      asunto: new FormControl('',[Validators.required]),
      mensaje: new FormControl('',[Validators.required])
    })
  }
  sendEmail(){
    let params = {
      email:this.datos.value.correo,
      name:this.datos.value.nombre,
      asunto:this.datos.value.asunto,
      mensaje:this.datos.value.mensaje
    }
    this.httpclien.post('https://localhost:8443/ápi/contact',params).subscribe(resp=>{
      console.log(resp)
    })
  }
}
