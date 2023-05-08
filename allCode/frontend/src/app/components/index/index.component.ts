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
    this.prueba(); 
  }

  irAPagina() {
    var menuDesplegable = document.getElementById("menuDesplegable") as HTMLSelectElement;
    var valorSeleccionado = menuDesplegable?.options[menuDesplegable.selectedIndex].value;
    window.location.href = valorSeleccionado;
  }

  prueba() {
    //   $(window).on("scroll", function () {
    //     var scroll = $(window).scrollTop();

    //     if (scroll >= 80) {
    //       $("#site-header").addClass("nav-fixed");
    //     } else {
    //       $("#site-header").removeClass("nav-fixed");
    //     }
    //   });

    //   //Main navigation Active Class Add Remove
    //   $(".navbar-toggler").on("click", function () {
    //     $("header").toggleClass("active");
    //   });
    //   $(document).on("ready", function () {
    //     if ($(window).width() > 991) {
    //       $("header").removeClass("active");
    //     }
    //     $(window).on("resize", function () {
    //       if ($(window).width() > 991) {
    //         $("header").removeClass("active");
    //       }
    //     });
    //   });
    window.addEventListener("scroll", function () {
      const scroll = window.pageYOffset;

      const header = document.getElementById("site-header") as HTMLElement;

      if (scroll >= 80) {
        header.classList.add("nav-fixed");
      } else {
        header.classList.remove("nav-fixed");
      }
    });
    const navbarToggler = document.querySelector(".navbar-toggler") as HTMLElement;

    navbarToggler.addEventListener("click", function () {
      const header = document.querySelector("header") as HTMLElement;
      header.classList.toggle("active");
    });

    window.addEventListener("load", function () {
      if (window.innerWidth > 991) {
        const header = document.querySelector("header") as HTMLElement;
        header.classList.remove("active");
      }

      window.addEventListener("resize", function () {
        if (window.innerWidth > 991) {
          const header = document.querySelector("header") as HTMLElement;
          header.classList.remove("active");
        }
      });
    });

  }
}
