import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { DestinationComponent } from './components/destination/destination.component';
import { PrivacyPolicyComponent } from './components/privacy-policy/privacy-policy.component';
import { ErrorPageComponent } from './components/error-page/error-page.component';
import { HttpClientModule } from '@angular/common/http';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { NgbdNavBasic } from "./components/Headers/header-login.component";

@NgModule({
    declarations: [
        AppComponent, DestinationComponent, PrivacyPolicyComponent, ErrorPageComponent
    ],
    providers: [],
    bootstrap: [AppComponent],
    imports: [
        BrowserModule,
        AppRoutingModule,
        HttpClientModule,
        NgbModule,
        NgbdNavBasic
    ]
})
export class AppModule { }
