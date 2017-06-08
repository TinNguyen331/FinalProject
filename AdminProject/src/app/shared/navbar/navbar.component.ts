import { Component, OnInit } from '@angular/core';
import { ROUTES } from '../../sidebar/sidebar-routes.config';
import { MenuType } from '../.././sidebar/sidebar.metadata';
import { Router, ActivatedRoute } from '@angular/router';
import { Location, LocationStrategy, PathLocationStrategy } from '@angular/common';

import { NotifyService } from '../../service/NotifyService/notify.service';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css'],
  providers:[NotifyService]
})
export class NavbarComponent implements OnInit {

  private listTitles: any[];
  location: Location;
  private listNotify:any[];
  private listNotifyLenght:number=0;
  constructor(location: Location,private notifyService:NotifyService) {
    this.location = location;
  }

  ngOnInit() {
    this.listTitles = ROUTES.filter(listTitle => listTitle.menuType !== MenuType.BRAND);
    this.loadNotify();
    //console.log(this.listNotify);
}

  getTitle() {
    var titlee = this.location.prepareExternalUrl(this.location.path());
    if (titlee.charAt(0) === '#') {
      titlee = titlee.slice(2);
    }
    for (var item = 0; item < this.listTitles.length; item++) {
      if (this.listTitles[item].path === titlee) {
        return this.listTitles[item].title;
      }
    }
    return 'Dashboard';
  }
  getPath() {
     //console.log(this.location);
    return this.location.prepareExternalUrl(this.location.path());
  }

  loadNotify(){
    this.notifyService.GetAllNotifyLongPolling().subscribe((response:any)=>{
      this.listNotify=response;
      this.listNotifyLenght=this.listNotify.length;
    },error=>console.log(error),()=>{
      console.log("Finished");
      console.log(this.loadNotify)});
    //console.log(this.listNotify);
  }

}
