//Module
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule, Routes } from '@angular/router';
import { ActivatedRoute } from '@angular/router';
import { FormsModule } from '@angular/forms'
import { NavbarModule } from '../shared/navbar/navbar.module';
import { FooterModule } from '../shared/footer/footer.module';
import { SidebarModule } from '../sidebar/sidebar.module';
import { CKEditorModule } from 'ng2-ckeditor';
//Component
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

import { ChangePasswordComponent } from './home/changepassword/changepassword.component';



const appRoutes: Routes = [
  {
    path: '', component: DashboardComponent,
    children: [
      { path: 'account', component: HomeComponent },
      { path: 'account/changepassword', component: ChangePasswordComponent },
      {
        path: 'category', component: CategoryComponent, children: [
          { path: 'add', redirectTo: 'category/add', pathMatch: 'full' },
        ]
      },
      { path: 'category/add', component: InsertCategoryComponent },
      { path: 'category/edit/:id', component: UpdateCategoryComponent },
      {
        path: 'product', component: ProductComponent, children: [
          { path: 'add', redirectTo: 'product/add', pathMatch: 'full' },
        ]
      },
      { path: 'product/add', component: InsertProductComponent },
      { path: 'product/edit/:id', component: UpdateProductComponent },
      {
        path: 'user', component: UserComponent, children: [
          { path: 'add', redirectTo: 'user/add', pathMatch: 'full' }
        ]
      },
      { path: 'user/add', component: InsertUserComponent },
      { path: 'user/edit/:id', component: UpdateUserComponent },
       {
        path: 'blog', component: BlogComponent, children: [
          { path: 'add', redirectTo: 'blog/add', pathMatch: 'full' }
        ]
      },
      { path: 'blog/add', component: InsertBlogComponent },
      { path: 'blog/edit/:id', component: UpdateBlogComponent },
      {
        path: 'event', component: EventComponent, children: [
          { path: 'add', redirectTo: 'event/add', pathMatch: 'full' }
        ]
      },
      { path: 'event/add', component: InsertEventComponent },
      { path: 'event/edit/:id', component: UpdateEventComponent },
      { path: 'specialday', component: SpecialDayComponent },
      { path: 'notify', component: NotifyComponent },
      { path: 'notify/view/:id', component: ViewNotifyComponent },
      { path: 'order', component: OrderComponent },
      { path: 'order/edit:/id', component: UpdateOrderComponent },
      { path: 'pricebyday', component: PriceByDayComponent },
      {
        path: 'meaning', component: MeaningComponent, children: [
          { path: 'add', redirectTo: 'meaning/add', pathMatch: 'full' }
        ]
      },
      { path: 'meaning/add', component: InsertMeaningComponent },
      { path: 'meaning/edit/:id', component: UpdateMeaningComponent }
    ]
  }
  /*{ path: '', redirectTo: 'account', pathMatch: 'full' },
  { path: 'account', component: HomeComponent  },
  { path: 'account/changepassword', component: ChangePasswordComponent  },*/
  /*{
    path: 'category', component: CategoryComponent , children: [
      { path: 'add', redirectTo: 'category/add', pathMatch: 'full' },
    ]
  },
  { path: 'category/add', component: InsertCategoryComponent  },
  { path: 'category/edit/:id', component: UpdateCategoryComponent  },
  {
    path: 'product', component: ProductComponent, outlet:'dash',children: [
      { path: 'add', redirectTo: 'product/add', pathMatch: 'full' },
    ]
  },
  { path: 'product/add', component: InsertProductComponent  },
  { path: 'product/edit/:id', component: UpdateProductComponent  },
  {
    path: 'user', component: UserComponent , children: [
      { path: 'add', redirectTo: 'user/add', pathMatch: 'full' }
    ]
  },
  { path: 'user/add', component: InsertUserComponent  },
  { path: 'user/edit/:id', component: UpdateUserComponent  },
  {
    path: 'blog', component: BlogComponent, outlet:'dash',children: [
      { path: 'add', redirectTo: 'blog/add', pathMatch: 'full' }
    ]
  },
  { path: 'blog/add', component: InsertBlogComponent  },
  { path: 'blog/edit/:id', component: UpdateBlogComponent  },
  {
    path: 'event', component: EventComponent , children: [
      { path: 'add', redirectTo: 'event/add', pathMatch: 'full' }
    ]
  },
  { path: 'event/add', component: InsertEventComponent  },
  { path: 'event/edit/:id', component: UpdateEventComponent  },
  { path: 'specialday', component: SpecialDayComponent  },
  { path: 'notify', component: NotifyComponent  },
  { path: 'notify/view/:id', component: ViewNotifyComponent  },
  { path: 'order', component: OrderComponent  },
  { path: 'order/edit:/id', component: UpdateOrderComponent  },
  { path: 'pricebyday', component: PriceByDayComponent  },
  {
    path: 'meaning', component: MeaningComponent , children: [
      { path: 'add', redirectTo: 'meaning/add', pathMatch: 'full' }
    ]
  },
  { path: 'meaning/add', component: InsertMeaningComponent  },
  { path: 'meaning/edit/:id', component: UpdateMeaningComponent  }
*/
]

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    CKEditorModule,
    NavbarModule,
    FooterModule,
    SidebarModule,
    RouterModule.forChild(appRoutes)
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
    ChangePasswordComponent],
  exports: [DashboardComponent]
})
export class DashboardModule { }
