
import { RouterModule, Routes } from '@angular/router';
import { AppComponent } from './app.component';
import { DestinationComponent } from './components/destination/destination.component';
import { ErrorPageComponent } from './components/error-page/error-page.component';

const appRoutes = [
  {path:'', component: AppComponent},
  {path:'index', component:AppComponent},
  {path: 'services', component:DestinationComponent},
  {path:'**', component:ErrorPageComponent},
]

export const routing = RouterModule.forRoot(appRoutes);