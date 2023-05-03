import { Component, OnInit, ViewChild, AfterViewInit, ElementRef } from '@angular/core';
import Chart from 'chart.js/auto';
import { RatingService } from 'src/app/services/rating.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-rating',
  templateUrl: './rating.component.html',
  styleUrls: ['./rating.component.css']
})
export class RatingComponent implements OnInit {

  @ViewChild('myChartCanvas') myChartCanvas: any;


  datos: any[] = [];
  valores: number[] = [];

  constructor(
    private router: Router,
    private ratingService: RatingService,
  ) { }

      ngOnInit() {
        this.ratingService.getRatings().subscribe((data: any[]) => {
          this.datos = data;
          this.valores = new Array(this.datos.length);
          this.valores[0] = this.datos[0].totalRating;
          this.valores[1] = this.datos[1].totalRating;
          this.valores[2] = this.datos[2].totalRating;
          this.valores[3] = this.datos[3].totalRating;
          this.valores[4] = this.datos[4].totalRating;
          this.valores[5] = this.datos[5].totalRating;
          this.valores[6] = this.datos[6].totalRating;
          this.valores[7] = this.datos[7].totalRating;

          const myChart = new Chart(this.myChartCanvas.nativeElement, {
            type: 'bar',
            data: {
              labels: [' ', 'París', 'Bangkok', 'Maldivas', 'Atenas', 'Londres', 'Alpes-Julianos', 'Santa-Marta', 'Singapur', ' '],
              datasets: [{
                label: 'Opinión en 2022',
                data: [1, this.valores[0], this.valores[1],this.valores[2], this.valores[3],this.valores[4], this.valores[5], this.valores[6], this.valores[7],5],
                backgroundColor: [
                  'transparent',
                  '#F007B8',
                  '#142A84',
                  '#F0F007',
                  '#9D5F0A',
                  'red',
                  'green',
                  '#0DEFDE',
                  'orange',
                  'transparent'
                ],
                borderWidth: 0
              }]
            },
            options: {
              scales: {
                y: {
                  min: 0,
                  max: 5
                }
              },
            }
          });
        });
      }

        };


  /* yValues = [0, this.valores[0], this.valores[1], this.valores[2], this.valores[3], this.valores[4], this.valores[5], this.valores[6], this.valores[7], 5];
  v=this.valores[0]; */
/*
  ngAfterViewInit() {
    let m=this.valores[1];
    const myChart = new Chart(this.myChartCanvas.nativeElement, {
      type: 'bar',
      data: {
        labels: [' ','París', 'Bangkok', 'Maldivas', 'Atenas', 'Londres', 'Alpes-Julianos', 'Santa-Marta', 'Singapur', ' '],
        datasets: [{
          label: 'Opinión en 2022',
          data: [1,2,m,4],
          backgroundColor: [
            'transparent',
            '#F007B8',
            '#142A84',
            '#F0F007',
            '#9D5F0A',
            'red',
            'green',
            '#0DEFDE',
            'orange',
            'transparent'
          ],
          borderWidth: 0
        }]
      },
      options: {
        scales: {
          y: {
            min: 0,
            max: 5
          }
        },
      }
    });
  }
 */




