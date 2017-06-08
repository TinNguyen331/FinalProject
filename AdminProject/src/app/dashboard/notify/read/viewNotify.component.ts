import { Component, OnInit, ElementRef } from '@angular/core';
import { Location } from '@angular/common';
import { Router, ActivatedRoute, NavigationEnd } from '@angular/router';
import { Subscription } from 'rxjs';
import 'rxjs/add/operator/filter';

import { NotifyService } from '../../../service/NotifyService/notify.service';
declare var $: any;
declare var swal: any;

@Component({
    moduleId: module.id,
    selector: 'app-viewNotify',
    templateUrl: 'viewNotify.component.html',
    providers: [NotifyService]
})

export class ViewNotifyComponent implements OnInit {

    public notify: any;
    public subscription: Subscription;
    public id: string;

    constructor(private el: ElementRef, private _location: Location,
        private notifyService: NotifyService, private router: Router,
        private activeRoute: ActivatedRoute) {
        //Code constructor
        router.events.filter(event => event instanceof NavigationEnd).subscribe((val) => {
            this.onChangeUrl();
        });
    }
    ngOnInit() {
        this.subscription = this.activeRoute.params.subscribe(params => {
            this.id = params['id'];
        });
        this.notifyService.UpdateNotify(this.id).subscribe(() => {
            console.log("Update status");
        });
    }
    backClick() {
        this._location.back();
    }
    onChangeUrl() {
        this.subscription = this.activeRoute.params.subscribe(params => {
            this.id = params['id'];
        });
        this.notifyService.GetNotifyById(this.id).subscribe((response: any) => {
            this.notify = response;
        });
        
    }
}