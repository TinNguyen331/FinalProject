import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';
import { CommonModule } from '@angular/common';
import { Routes, RouterModule } from "@angular/router";

import { AppComponent } from './app.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import { DashboardModule } from './dashboard/dashboard.module';

import { LoginComponent } from './main/login/login.component';
import { RegisterComponent } from './main/register/register.component';

import { AuthGuard } from './_guard/auth.guard';

import { HashLocationStrategy, LocationStrategy } from '@angular/common';

const appRoutes:Routes=[
  {
    path:'dashboard',canActivate: [AuthGuard],  
    loadChildren:'app/dashboard/dashboard.module#DashboardModule'
  },
  { path:'',component:LoginComponent},
  { path: 'dashboard', redirectTo: '' }
 
]

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    RegisterComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpModule,
    RouterModule.forRoot(appRoutes)
  ],
  providers: [{ provide: LocationStrategy, useClass: HashLocationStrategy },AuthGuard],
  bootstrap: [AppComponent]
})
export class AppModule { }
