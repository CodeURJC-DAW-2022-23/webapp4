import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { DestinationComponent } from './components/destination/destination.component';
import { ErrorPageComponent } from './components/error-page/error-page.component';

const routes: Routes = [
  {path:'', redirectTo:'/index', pathMatch:'full'},
  {path:'index', component:DestinationComponent},
  {path:'**', component:ErrorPageComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
