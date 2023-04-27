
import { RouterModule, Routes } from '@angular/router';
import { AppComponent } from './app.component';
import { DestinationComponent } from './components/destination/destination.component';
import { ErrorPageComponent } from './components/error-page/error-page.component';
import { InformationComponent } from './components/destination/information/information.component';
import { CateringComponent } from './components/destination/catering/catering.component';

const appRoutes = [
  {path:'index', component:AppComponent},
  {path: 'services', component:DestinationComponent},
  {path: 'services/:id', component:InformationComponent},
  {path: 'catering/:id', component:CateringComponent},


  
  {path:'', component: AppComponent},
  {path:'**', component:ErrorPageComponent},
]

export const routing = RouterModule.forRoot(appRoutes);