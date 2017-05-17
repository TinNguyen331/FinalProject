"use strict";
var home_component_1 = require('./home/home.component');
var manageCategory_component_1 = require('./manageCategory/manageCategory.component');
var manageEvent_component_1 = require('./manageEvent/manageEvent.component');
var manageNotify_component_1 = require('./manageNotify/manageNotify.component');
var manageOrder_component_1 = require('./manageOrder/manageOrder.component');
var manageProduct_component_1 = require('./manageProduct/manageProduct.component');
var manageSpecialDay_component_1 = require('./manageSpecialDay/manageSpecialDay.component');
var manageUser_component_1 = require('./manageUser/manageUser.component');
var managePrice_component_1 = require('./managePrice/managePrice.component');
var viewCategory_component_1 = require('./viewCategory/viewCategory.component');
var viewEvent_component_1 = require('./viewEvent/viewEvent.component');
var viewNotify_component_1 = require('./viewNotify/viewNotify.component');
var viewOrder_component_1 = require('./viewOrder/viewOrder.component');
var viewProduct_component_1 = require('./viewProduct/viewProduct.component');
var viewSpecialDay_component_1 = require('./viewSpecialDay/viewSpecialDay.component');
var viewUser_component_1 = require('./viewUser/viewUser.component');
exports.MODULE_ROUTES = [
    { path: 'dashboard', component: home_component_1.HomeComponent },
    { path: 'manageCategory', component: manageCategory_component_1.ManageCategoryComponent },
    { path: 'manageEvent', component: manageEvent_component_1.ManageEventComponent },
    { path: 'manageNotify', component: manageNotify_component_1.ManageNotifyComponent },
    { path: 'manageOrder', component: manageOrder_component_1.ManageOrderComponent },
    { path: 'manageProduct', component: manageProduct_component_1.ManageProductComponent },
    { path: 'manageSpecialDay', component: manageSpecialDay_component_1.ManageSpecialDayComponent },
    { path: 'manageUser', component: manageUser_component_1.ManageUserComponent },
    { path: 'managePrice', component: managePrice_component_1.ManagePriceComponent },
    { path: 'viewCategory', component: viewCategory_component_1.ViewCategoryComponent },
    { path: 'viewEvent', component: viewEvent_component_1.ViewEventComponent },
    { path: 'viewNotify', component: viewNotify_component_1.ViewNotifyComponent },
    { path: 'viewOrder', component: viewOrder_component_1.ViewOrderComponent },
    { path: 'viewProduct', component: viewProduct_component_1.ViewProductComponent },
    { path: 'viewSpecialDay', component: viewSpecialDay_component_1.ViewSpecialDayComponent },
    { path: 'viewUser', component: viewUser_component_1.ViewUserComponent },
    { path: '', redirectTo: 'dashboard', pathMatch: 'full' }
];
exports.MODULE_COMPONENTS = [
    home_component_1.HomeComponent,
    manageCategory_component_1.ManageCategoryComponent,
    manageEvent_component_1.ManageEventComponent,
    manageNotify_component_1.ManageNotifyComponent,
    manageOrder_component_1.ManageOrderComponent,
    manageProduct_component_1.ManageProductComponent,
    manageSpecialDay_component_1.ManageSpecialDayComponent,
    manageUser_component_1.ManageUserComponent,
    managePrice_component_1.ManagePriceComponent,
    viewCategory_component_1.ViewCategoryComponent,
    viewEvent_component_1.ViewEventComponent,
    viewNotify_component_1.ViewNotifyComponent,
    viewOrder_component_1.ViewOrderComponent,
    viewProduct_component_1.ViewProductComponent,
    viewSpecialDay_component_1.ViewSpecialDayComponent,
    viewUser_component_1.ViewUserComponent
];
//# sourceMappingURL=dashboard.routes.js.map