import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule, Routes } from '@angular/router';
import { ActivatedRoute } from '@angular/router';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule } from '@angular/forms'
import { DashboardComponent } from './dashboard.component';
import { HomeComponent } from './home/home.component';

import { CategoryComponent } from './category/category.component';
import { InsertCategoryComponent } from './category/insert/insertCategory.component';
import { UpdateCategoryComponent } from './category/update/updateCategory.component';

import { ProductComponent } from './product/product.component';
import { InsertProductComponent } from './product/insert/insertProduct.component';
import { UpdateProductComponent } from './product/update/updateProduct.component';

import { UserComponent } from './user/user.component';
import { InsertUserComponent } from './user/insert/insertUser.component';
import { UpdateUserComponent } from './user/update/updateUser.component';

import { BlogComponent } from './blog/blog.component';
import { InsertBlogComponent } from './blog/insert/insertBlog.component';
import { UpdateBlogComponent } from './blog/update/updateBlog.component';

import { EventComponent } from './event/event.component';
import { InsertEventComponent } from './event/insert/insertEvent.component';
import { UpdateEventComponent } from './event/update/updateEvent.component';

import { SpecialDayComponent } from './specialday/specialday.component';

import { NotifyComponent } from './notify/notify.component';
import { ViewNotifyComponent } from './notify/read/viewNotify.component';

import { OrderComponent } from './order/order.component';
import { UpdateOrderComponent } from './order/update/updateOrder.component';

import { PriceByDayComponent } from './pricebyday/pricebyday.component';

import { MeaningComponent } from './meaning/meaning.component';
import { UpdateMeaningComponent } from './meaning/update/updateMeaning.component';
import { InsertMeaningComponent } from './meaning/insert/insertMeaning.component';

import { LoginComponent } from './login/login.component';
import { ChangePasswordComponent } from './home/changepassword/changepassword.component';

import { CKEditorModule } from 'ng2-ckeditor';

const appRoutes: Routes = [
  { path: '', redirectTo: 'account', pathMatch: 'full' },
  { path: 'account', component: HomeComponent },
  { path: 'account/changepassword', component: ChangePasswordComponent },
  { path: 'category', component: CategoryComponent },
  { path: 'category/add', component: InsertCategoryComponent },
  { path: 'category/view', component: UpdateCategoryComponent },
  { path: 'product', component: ProductComponent },
  { path: 'product/add', component: InsertProductComponent },
  { path: 'product/view', component: UpdateProductComponent },
  { path: 'user', component: UserComponent },
  { path: 'user/add', component: InsertUserComponent },
  { path: 'user/view', component: UpdateUserComponent },
  { path: 'blog', component: BlogComponent },
  { path: 'blog/add', component: InsertBlogComponent },
  { path: 'blog/view', component: UpdateBlogComponent },
  { path: 'event', component: EventComponent },
  { path: 'event/add', component: InsertEventComponent },
  { path: 'event/view', component: UpdateEventComponent },
  { path: 'specialday', component: SpecialDayComponent },
  { path: 'notify', component: NotifyComponent },
  { path: 'notify/view', component: ViewNotifyComponent },
  { path: 'order', component: OrderComponent },
  { path: 'order/update', component: UpdateOrderComponent },
  { path: 'pricebyday', component: PriceByDayComponent },
  { path: 'meaning', component: MeaningComponent },
  { path: 'meaning/update', component: UpdateMeaningComponent },
  { path: 'meaning/insert', component: InsertMeaningComponent }

]
@NgModule({
  imports: [
    CommonModule,
    BrowserModule,
    FormsModule,
    CKEditorModule,
    RouterModule.forRoot(appRoutes)
  ],
  declarations: [
    DashboardComponent,
    HomeComponent,
    CategoryComponent,
    InsertCategoryComponent,
    UpdateCategoryComponent,
    ProductComponent,
    InsertProductComponent,
    UpdateProductComponent,
    UserComponent,
    InsertUserComponent,
    UpdateUserComponent,
    BlogComponent,
    InsertBlogComponent,
    UpdateBlogComponent,
    EventComponent,
    InsertEventComponent,
    UpdateEventComponent,
    SpecialDayComponent,
    NotifyComponent,
    ViewNotifyComponent,
    OrderComponent,
    UpdateOrderComponent,
    PriceByDayComponent,
    MeaningComponent,
    UpdateMeaningComponent, 
    InsertMeaningComponent,
    LoginComponent,
    ChangePasswordComponent],
  exports: [DashboardComponent]
})
export class DashboardModule { }
