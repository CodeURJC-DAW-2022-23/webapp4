
import { RouterModule, Routes } from '@angular/router';
import { AppComponent } from './app.component';
import { DestinationComponent } from './components/destination/destination.component';
import { ErrorPageComponent } from './components/error-page/error-page.component';
import { InformationComponent } from './components/destination/information/information.component';
import { CateringComponent } from './components/destination/catering/catering.component';
import { RatingComponent } from './components/rating/rating.component';
import { RegisterComponent } from './components/register/register.component';
import { LoginComponent } from './components/login/login.component';
import { HeaderLogin } from './components/Headers/header.component';
import { ProfileComponent } from './components/profile/profile.component';
import { TourismComponent } from './components/destination/tourism/tourism.component';
import { ReviewComponent } from "./components/destination/review/review.component";
import { PurchaseComponent } from "./components/destination/purchase/purchase.component";
import { ContactComponent } from "./components/contact/contact.component";
import { PrivacyPolicyComponent } from './components/privacy-policy/privacy-policy.component';
import { TermsComponent } from './components/terms/terms.component';
import { AdministratorComponent } from './components/administrator/administrator.component';
import { IndexComponent } from './components/index/index.component';
import { HouseComponent } from './components/destination/house/house.component';


const appRoutes = [
  { path: 'index', component: IndexComponent },
  { path: 'services', component: DestinationComponent },
  { path: 'services/:id', component: InformationComponent },
  { path: 'catering/:id', component: CateringComponent },
  { path: 'tourism/:id', component: TourismComponent },
  { path: 'house/:id', component: HouseComponent },
  { path: 'rating', component: RatingComponent },
  { path: 'register', component: RegisterComponent },
  { path: 'login', component: LoginComponent },
  { path: 'profile', component: ProfileComponent },
  { path: 'review', component: ReviewComponent },
  { path: 'contact', component: ContactComponent },
  { path: 'purchase/:id', component: PurchaseComponent },
  { path: 'privacyPolicy', component: PrivacyPolicyComponent },
  { path: 'terms', component: TermsComponent },
  { path: 'administrator', component: AdministratorComponent },

  { path: '', component: IndexComponent },
  { path: '**', component: ErrorPageComponent },
]

export const routing = RouterModule.forRoot(appRoutes);
