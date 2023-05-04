
import { RouterModule, Routes } from '@angular/router';
import { AppComponent } from './app.component';
import { DestinationComponent } from './components/destination/destination.component';
import { ErrorPageComponent } from './components/error-page/error-page.component';
import { InformationComponent } from './components/destination/information/information.component';
import { CateringComponent } from './components/destination/catering/catering.component';
import { RatingComponent } from './components/rating/rating.component';
import { RegisterComponent } from './components/register/register.component';
import { LoginComponent } from './components/login/login.component';

import { ProfileComponent } from './components/profile/profile.component';

import { TourismComponent } from './components/destination/tourism/tourism.component';


const appRoutes = [
  { path: 'index', component: AppComponent },
  { path: 'services', component: DestinationComponent },
  { path: 'services/:id', component: InformationComponent },
  { path: 'catering/:id', component: CateringComponent },
  { path: 'tourism/:id', component: TourismComponent },
  { path: 'rating', component: RatingComponent },
  { path: 'register', component: RegisterComponent },
  { path: 'login', component: LoginComponent },
  { path: 'profile', component: ProfileComponent },



  { path: '', component: AppComponent },
  { path: '**', component: ErrorPageComponent },
]

export const routing = RouterModule.forRoot(appRoutes);
