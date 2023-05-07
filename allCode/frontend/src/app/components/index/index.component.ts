import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Destination } from 'src/app/models/destination.model';
import { DestinationService } from 'src/app/services/destination.service';

@Component({
  selector: 'app-index',
  templateUrl: './index.component.html',
  styleUrls: ['./index.component.css']
})
export class IndexComponent implements OnInit {
  destinations: Destination[] = [];


  constructor(
    private destinationService: DestinationService
  ) {}

  ngOnInit(): void {
    this.destinationService.getDestinations().subscribe((response) => {
      this.destinations = response.content;
    }); 
  }

   irAPagina() {
    var menuDesplegable = document.getElementById("menuDesplegable") as HTMLSelectElement;
    var valorSeleccionado = menuDesplegable?.options[menuDesplegable.selectedIndex].value;
    window.location.href = valorSeleccionado;
    }
}
