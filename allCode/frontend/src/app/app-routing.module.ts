import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { DestinationComponent } from './components/destination/destination.component';
import { ErrorPageComponent } from './components/error-page/error-page.component';
import { PrivacyPolicyComponent } from './components/privacy-policy/privacy-policy.component';
import { AppComponent } from './app.component';

const routes: Routes = [
  { path: '', redirectTo: '/index', pathMatch: 'full', title: 'Inicio' },
  { path: 'index', component: AppComponent, title: 'Inicio' },
  { path: '**', component: ErrorPageComponent, title: 'Error404' },
  { path: 'services', component: DestinationComponent, title: 'Destinos' },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
