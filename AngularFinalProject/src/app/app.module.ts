import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';

import { MODULE_COMPONENT } from './app.routes';
import { APP_BASE_HREF } from '@angular/common';

import { AppComponent } from './app.component';
import { HomeComponent } from './home/home.component';
import { IntroduceComponent } from './home2/introduce/introduce.component';
import { BlogComponent } from './home2/blog/blog.component';
import { ContactComponent } from './home2/contact/contact.component';
import { ProductComponent } from './home2/product/product.component';
import { ExperienceComponent } from './home2/experience/experience.component';
import { PartnerComponent } from './home2/partner/partner.component';
import { FanpageComponent } from './home2/fanpage/fanpage.component';
import { FlMeaningComponent } from './home2/flmeaning/flmeaning.component';
import { ProductDetailsComponent } from './home2/productdetails/productdetails.component';
import { HeaderComponent } from './home2/header/header.component';
import { FooterComponent } from './home2/footer/footer.component';

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    IntroduceComponent,
    HeaderComponent,
    FooterComponent,
    BlogComponent,
    ContactComponent,
    ProductComponent,
    ExperienceComponent,
    PartnerComponent,
    FanpageComponent,
    FlMeaningComponent,
    ProductDetailsComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpModule,
    MODULE_COMPONENT
  ],
  providers: [{ provide: APP_BASE_HREF, useValue: '/' }],
  bootstrap: [AppComponent]
})
export class AppModule { }
