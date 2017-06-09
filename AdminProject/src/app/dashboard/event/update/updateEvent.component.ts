import { Component, OnInit } from '@angular/core';
import { Location } from '@angular/common';
import initDatetimepickers = require('../../../../assets/js/init/initDatetimepickers.js');
import { ProductService } from '../../../service/ProductService/product.service';
import { EventService } from '../../../service/eventService/event.service';
import { EventModel } from '../event.model';
import { ProductInEventModel } from '../productInEvent.model';
import { InsertEventModel } from '../insertEvent.model';
import { Router, ActivatedRoute, NavigationEnd } from '@angular/router';
import { Subscription } from 'rxjs';


declare var $: any;
declare var swal: any;

@Component({
    moduleId: module.id,
    selector: 'app-updateEvent',
    templateUrl: 'updateEvent.component.html',
    providers: [ProductService, EventService]
})

export class UpdateEventComponent implements OnInit {

    public discountProducts: any[];
    public subscription: Subscription;
    public id: string;
    public event: any;
    

    constructor(private _location: Location, private productService: ProductService,
        private eventService: EventService, private router: Router,
        private activeRoute: ActivatedRoute) {
        router.events.filter(event => event instanceof NavigationEnd).subscribe((val) => {
            this.onChangeUrl();
        });
    }

    onChangeUrl() {
        this.subscription = this.activeRoute.params.subscribe(params => {
            this.id = params['id'];
        });
        this.eventService.GetEventByID(this.id).subscribe((response: any) => {
            this.event = response;
            var fromDate=new Date(response.fromDate);
            var toDay=new Date(response.toDate);
            this.discountProducts=response.discountProducts;
            console.log(response);
        });
    }

    backClick() {
        this._location.back();
    }

    ngOnInit() {
        $.getScript('../../../../assets/js/plugins/bootstrap-datetimepicker.js');
        //$.getScript('../../../assets/js/init/initDataTable.js');
        initDatetimepickers();
    }
}