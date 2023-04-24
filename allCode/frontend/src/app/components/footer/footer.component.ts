import { Component } from '@angular/core';

@Component({
  selector: 'app-footer',
  templateUrl: './footer.component.html',
  styleUrls: ['./footer.component.css']
  
})

export class FooterComponent {
}
//   // Import jQuery
// import $ from 'jquery';

// // Import required JavaScript files
// import './assets/js/theme-change.js';
// import './assets/js/jquery.zoomslider.min.js';
// import './assets/js/owl.carousel.js';
// import './assets/js/jquery.waypoints.min.js';
// import './assets/js/jquery.countup.js';
// import './assets/js/bootstrap.min.js';

// // Testimonials carousel slider
// $(document).ready(() => {
//   $("#owl-demo1").owlCarousel({
//     loop: true,
//     margin: 20,
//     nav: false,
//     responsiveClass: true,
//     responsive: {
//       0: {
//         items: 1,
//         nav: false
//       },
//       736: {
//         items: 1,
//         nav: false
//       },
//       1000: {
//         items: 1,
//         nav: false,
//         loop: true
//       }
//     }
//   });
// });

// // Header navigation functionality
// $(window).on("scroll", () => {
//   const scroll = $(window).scrollTop();

//   if (scroll >= 80) {
//     $("#site-header").addClass("nav-fixed");
//   } else {
//     $("#site-header").removeClass("nav-fixed");
//   }
// });

// $(".navbar-toggler").on("click", () => {
//   $("header").toggleClass("active");
// });

// $(document).on("ready", () => {
//   if ($(window).width() > 991) {
//     $("header").removeClass("active");
//   }

//   $(window).on("resize", () => {
//     if ($(window).width() > 991) {
//       $("header").removeClass("active");
//     }
//   });
// });

// // Redirect function
// function irAPagina() {
//   const menuDesplegable = document.getElementById("menuDesplegable") as HTMLSelectElement;
//   const valorSeleccionado = menuDesplegable.options[menuDesplegable.selectedIndex].value;
//   window.location.href = valorSeleccionado;
// }

// // Activate stats counter
// $('.counter').countUp();

// }
