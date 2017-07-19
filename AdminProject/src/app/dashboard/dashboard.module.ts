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
import { ViewOrderComponent } from './order/view/viewOrder.component';
import { PriceByDayComponent } from './pricebyday/pricebyday.component';
import { MeaningComponent } from './meaning/meaning.component';
import { UpdateMeaningComponent } from './meaning/update/updateMeaning.component';
import { InsertMeaningComponent } from './meaning/insert/insertMeaning.component';
import { ChangePasswordComponent } from './home/changepassword/changepassword.component';
import { ImportComponent } from './import/import.component';
import { ChartComponent } from './chart/chart.component';
import { PriceByDayDetailsComponent } from './pricebyday/details/pricebydaydetails.component';

import { OrderResolve } from './order/order.resolve';
import { OrderService } from '../service/OrderService/order.service';

import { AuthGuard } from '../_guard/auth.guard'; 

const appRoutes: Routes = [
  {
    path: '', component: DashboardComponent,
    children: [
      { path: '',component: ChartComponent,canActivate: [AuthGuard]},
      { path: 'account', component: HomeComponent ,canActivate: [AuthGuard]},
      { path: 'account/changepassword', component: ChangePasswordComponent,canActivate: [AuthGuard] },
      {
        path: 'category', component: CategoryComponent, children: [
          { path: 'add', redirectTo: 'category/add', pathMatch: 'full' ,canActivate: [AuthGuard]},
        ],
        canActivate: [AuthGuard]
      },
      { path: 'category/add', component: InsertCategoryComponent,canActivate: [AuthGuard] },
      { path: 'category/edit/:id', component: UpdateCategoryComponent,canActivate: [AuthGuard] },
      {
        path: 'product', component: ProductComponent, children: [
          { path: 'add', redirectTo: 'product/add', pathMatch: 'full',canActivate: [AuthGuard] },
        ],
        canActivate: [AuthGuard]
      },
      { path: 'product/add', component: InsertProductComponent ,canActivate: [AuthGuard]},
      { path: 'product/edit/:id', component: UpdateProductComponent,canActivate: [AuthGuard] },
      { path: 'user', component: UserComponent,canActivate: [AuthGuard] },
      {
        path: 'blog', component: BlogComponent, children: [
          { path: 'add', redirectTo: 'blog/add', pathMatch: 'full',canActivate: [AuthGuard] }
        ],
        canActivate: [AuthGuard]
      },
      { path: 'blog/add', component: InsertBlogComponent,canActivate: [AuthGuard] },
      { path: 'blog/edit/:id', component: UpdateBlogComponent ,canActivate: [AuthGuard]},

      {
        path: 'event', component: EventComponent, children: [
          { path: 'add', redirectTo: 'event/add', pathMatch: 'full' ,canActivate: [AuthGuard]},
        ],
        canActivate: [AuthGuard]
      },

      { path: 'event/add', component: InsertEventComponent ,canActivate: [AuthGuard]},
      { path: 'event/edit/:id', component: UpdateEventComponent ,canActivate: [AuthGuard]},

      { path: 'specialday', component: SpecialDayComponent ,canActivate: [AuthGuard]},
      { path: 'notify', component: NotifyComponent ,canActivate: [AuthGuard]},
      { path: 'notify/view/:id', component: ViewNotifyComponent ,canActivate: [AuthGuard]},
      { path: 'order', component: OrderComponent ,canActivate: [AuthGuard]},
      { path: 'order/view/:id', component: ViewOrderComponent,resolve:{order:OrderResolve} ,canActivate: [AuthGuard]},
      { path: 'pricebyday', component: PriceByDayComponent ,canActivate: [AuthGuard]},
      { path: 'pricebyday/view/:id', component: PriceByDayDetailsComponent ,canActivate: [AuthGuard]},
      {
        path: 'meaning', component: MeaningComponent, children: [
          { path: 'add', redirectTo: 'meaning/add', pathMatch: 'full',canActivate: [AuthGuard] }
        ],
        canActivate: [AuthGuard]
      },
      { path: 'meaning/add', component: InsertMeaningComponent ,canActivate: [AuthGuard]},
      { path: 'meaning/edit/:id', component: UpdateMeaningComponent ,canActivate: [AuthGuard]},

      { path: 'import', component: ImportComponent,canActivate: [AuthGuard] },
    ]
  }
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
    ViewOrderComponent,
    PriceByDayComponent,
    PriceByDayDetailsComponent,
    MeaningComponent,
    UpdateMeaningComponent,
    InsertMeaningComponent,
    ChangePasswordComponent,
    ImportComponent,
    ChartComponent
  ],
  providers:[OrderResolve,OrderService,AuthGuard]
  ,
  exports: [DashboardComponent]
})
export class DashboardModule { }
