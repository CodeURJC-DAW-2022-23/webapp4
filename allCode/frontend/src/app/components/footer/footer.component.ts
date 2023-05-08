import { Component, ChangeDetectionStrategy } from '@angular/core';
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {HttpClient} from "@angular/common/http";

@Component({
  selector: 'app-footer',
  templateUrl: './footer.component.html',
  styleUrls: ['./footer.component.css']
})
export class FooterComponent {
  correo: FormGroup;
  constructor (private httpclien:HttpClient){
    this.correo = new FormGroup({
      email: new FormControl('', [Validators.required, Validators.email])
    })
  }
  sendNewsletter(){

    let params = {
      email:this.correo.value.email,
    }
    this.httpclien.post('/api/newsletter/', params)
      .subscribe(resp => {
        console.log(resp);
      }, err => {
        console.log(err);
      });
  }
}
