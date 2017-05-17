import { Route } from '@angular/router';
import { DashboardComponent } from './dashboard.component';
import { HomeComponent } from './home/home.component';
import { ManageCategoryComponent } from './manageCategory/manageCategory.component';
import { ManageEventComponent } from './manageEvent/manageEvent.component';
import { ManageNotifyComponent } from './manageNotify/manageNotify.component';
import { ManageOrderComponent } from './manageOrder/manageOrder.component';
import { ManageProductComponent } from './manageProduct/manageProduct.component';
import { ManageSpecialDayComponent } from './manageSpecialDay/manageSpecialDay.component';
import { ManageUserComponent } from './manageUser/manageUser.component';
import { ManagePriceComponent } from './managePrice/managePrice.component';
import { ViewCategoryComponent } from './viewCategory/viewCategory.component';
import { ViewEventComponent } from './viewEvent/viewEvent.component';
import { ViewNotifyComponent } from './viewNotify/viewNotify.component';
import { ViewOrderComponent } from './viewOrder/viewOrder.component';
import { ViewProductComponent } from './viewProduct/viewProduct.component';
import { ViewSpecialDayComponent } from './viewSpecialDay/viewSpecialDay.component';
import { ViewUserComponent } from './viewUser/viewUser.component';

export const MODULE_ROUTES: Route[] = [
    { path: 'dashboard', component: HomeComponent },
    { path: 'manageCategory', component: ManageCategoryComponent },
    { path: 'manageEvent', component: ManageEventComponent },
    { path: 'manageNotify', component: ManageNotifyComponent },
    { path: 'manageOrder', component: ManageOrderComponent },
    { path: 'manageProduct', component: ManageProductComponent },
    { path: 'manageSpecialDay', component: ManageSpecialDayComponent },
    { path: 'manageUser', component: ManageUserComponent },
    { path: 'managePrice', component: ManagePriceComponent },
    { path: 'viewCategory', component: ViewCategoryComponent },
    { path: 'viewEvent', component: ViewEventComponent },
    { path: 'viewNotify', component: ViewNotifyComponent },
    { path: 'viewOrder', component: ViewOrderComponent },
    { path: 'viewProduct', component: ViewProductComponent },
    { path: 'viewSpecialDay', component: ViewSpecialDayComponent },
    { path: 'viewUser', component: ViewUserComponent },
    { path: '', redirectTo: 'dashboard', pathMatch: 'full' }
]

export const MODULE_COMPONENTS = [
    HomeComponent,
    ManageCategoryComponent,
    ManageEventComponent,
    ManageNotifyComponent,
    ManageOrderComponent,
    ManageProductComponent,
    ManageSpecialDayComponent,
    ManageUserComponent,
    ManagePriceComponent,
    ViewCategoryComponent,
    ViewEventComponent,
    ViewNotifyComponent,
    ViewOrderComponent,
    ViewProductComponent,
    ViewSpecialDayComponent,
    ViewUserComponent
]
