import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';

import { routing } from './app.routing';
import { AppComponent } from './app.component';
import { DestinationComponent } from './components/destination/destination.component';
import { PrivacyPolicyComponent } from './components/privacy-policy/privacy-policy.component';
import { ErrorPageComponent } from './components/error-page/error-page.component';
import { HeaderLogin } from "./components/headers/header.component";
import { InformationComponent } from './components/destination/information/information.component';
import { CateringComponent } from './components/destination/catering/catering.component';
import { RatingComponent } from './components/rating/rating.component';
import { RegisterComponent } from './components/register/register.component';
import { LoginComponent } from './components/login/login.component';
import { ContactComponent } from './components/contact/contact.component';
import { ReviewComponent } from './components/destination/review/review.component';
import { PurchaseComponent } from './components/destination/purchase/purchase.component';
import { ProfileComponent } from './components/profile/profile.component';
import { NgxPaginationModule } from 'ngx-pagination';
import { TourismComponent } from './components/destination/tourism/tourism.component';
import { FooterComponent } from './components/footer/footer.component';
import { TermsComponent } from './components/terms/terms.component';
import { AdministratorComponent } from './components/administrator/administrator.component';
import { IndexComponent } from './components/index/index.component';
import { HouseComponent } from './components/destination/house/house.component';


@NgModule({
    declarations: [
        AppComponent, DestinationComponent, PrivacyPolicyComponent, ErrorPageComponent, HeaderLogin, InformationComponent, CateringComponent,
        RatingComponent, RegisterComponent, LoginComponent, ProfileComponent, ReviewComponent, ContactComponent, PurchaseComponent, TourismComponent,
        FooterComponent, TermsComponent, AdministratorComponent, IndexComponent, HouseComponent,
    ],
    providers: [],
    bootstrap: [AppComponent],
    imports: [
        BrowserModule,
        HttpClientModule,
        FormsModule,
        NgxPaginationModule,
        routing,

    ],
})
export class AppModule { }
