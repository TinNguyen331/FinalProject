import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule, Routes } from '@angular/router';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule } from '@angular/forms'
import { DashboardComponent } from './dashboard.component';
import { HomeComponent } from './home/home.component';
import { CategoryComponent } from './category/category.component';



const appRoutes: Routes = [
  { path: '', redirectTo: 'dashboard', pathMatch: 'full' },
  { path: 'dashboard', component: HomeComponent },
  { path: 'category', component: CategoryComponent },
  
]
@NgModule({
  imports: [
    CommonModule,
    BrowserModule,
    FormsModule,
    RouterModule.forRoot(appRoutes)
  ],
  declarations: [
    DashboardComponent,
    HomeComponent,
    CategoryComponent
  ],
  exports: [DashboardComponent]
})
export class DashboardModule { }
