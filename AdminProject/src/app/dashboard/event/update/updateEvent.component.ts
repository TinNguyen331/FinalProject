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

    async onChangeUrl() {
        this.subscription = this.activeRoute.params.subscribe(params => {
            this.id = params['id'];
        });
        await this.eventService.GetEventByID(this.id).subscribe((response: any) => {
            response.fromDate = new Date(response.fromDate).toLocaleDateString();
            response.toDate = new Date(response.toDate).toLocaleDateString();
            this.event = response;
            this.discountProducts = response.discountProducts;
            console.log(response);
            
        });
        await $.getScript('../../../../assets/js/plugins/bootstrap-datetimepicker.js');
        await initDatetimepickers();

        //dung js set value cho cai 
       
    }

    backClick() {
        this._location.back();
    }

    ngOnInit() {
        
        //$.getScript('../../../assets/js/init/initDataTable.js');

    }
    initDatetimepickers(fromDate,toDate) {
        console.log(this.event);
        $('.datepickerfromdate').datetimepicker({
            format: 'MM/DD/YYYY',
            icons: {
                time: "fa fa-clock-o",
                date: "fa fa-calendar",
                up: "fa fa-chevron-up",
                down: "fa fa-chevron-down",
                previous: 'fa fa-chevron-left',
                next: 'fa fa-chevron-right',
                today: 'fa fa-screenshot',
                clear: 'fa fa-trash',
                close: 'fa fa-remove',
                inline: true
            }
        });
        $( ".datepickerfromdate" ).datepicker( "setDate", fromDate);
        $('.datepickertodate').datetimepicker({
            format: 'MM/DD/YYYY',
            icons: {
                time: "fa fa-clock-o",
                date: "fa fa-calendar",
                up: "fa fa-chevron-up",
                down: "fa fa-chevron-down",
                previous: 'fa fa-chevron-left',
                next: 'fa fa-chevron-right',
                today: 'fa fa-screenshot',
                clear: 'fa fa-trash',
                close: 'fa fa-remove',
                inline: true
            }
        });
        $( ".datepickertodate" ).datepicker( "setDate", toDate);
    }
}