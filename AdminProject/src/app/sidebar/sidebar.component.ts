import { Component, OnInit } from '@angular/core';
import {ROUTES} from './sidebar-routes.config';
import {MenuType} from './sidebar.metadata';

import { Router, ActivatedRoute } from '@angular/router';
import { AuthenticationService } from '../service/AuthenticationService/authentication.service';

declare var $:any;

@Component({
  selector: 'app-sidebar',
  templateUrl: './sidebar.component.html',
  styleUrls: ['./sidebar.component.css'],
  providers:[AuthenticationService]
})
export class SidebarComponent implements OnInit {

  public menuItems:any[];
  constructor(private auth:AuthenticationService,private router: Router) { }

  logout(){
    this.auth.logout();
    this.router.navigate(['/']);
  }
  ngOnInit() {
    $.getScript('../../assets/js/sidebar-moving-tab.js');
    this.menuItems=ROUTES.filter(menuItem=>menuItem.menuType!==MenuType.BRAND);
  };

}
