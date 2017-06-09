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

import { DiscountProductModel } from '../discountProduct.model';

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
    public listDiscountProductModel: DiscountProductModel[] = [];

    public listPro: any[];



    constructor(private _location: Location, private productService: ProductService,
        private eventService: EventService, private router: Router,
        private activeRoute: ActivatedRoute) {
        router.events.filter(event => event instanceof NavigationEnd).subscribe((val) => {
            this.onChangeUrl();

        });
        this.LoadProduct();
    }

    LoadProduct() {
        this.productService.GetAllProduct().subscribe((response: any) => {
            this.listPro = response;
            $.getScript('../../../assets/js/init/initDataTable.js');
        })
    }


    Notify(from: any, algin: string, message: string, type: string): void {
        $.notify({
            icon: "notifications",
            message: message

        }, {
                type: type,
                timer: 30,
                placement: {
                    from: from,
                    align: algin
                }
            });
    }
    addProInEvent(id, productName, productPrice) {
        if (!this.checkModelIsExits(id)) {
            var model = new DiscountProductModel();
            model.productId = id;
            model.productName = productName;
            model.productPrice = productPrice;

            this.listDiscountProductModel.push(model);
        }
        else {
            this.Notify('top', 'center', "Error: This product has been selected in this event!", 'danger');
        }

    }
    checkModelIsExits(id) {
        let index = this.listDiscountProductModel.findIndex(x => x.productId == id);
        if (index > -1)
            return true;
        return false;
    }
    removeProductInEvent(id) {
        if (this.checkModelIsExits(id)) {
            let index = this.listDiscountProductModel.findIndex(x => x.productId == id)
            this.listDiscountProductModel.splice(index, 1);
        }
        else {
            this.Notify('top', 'center', "Error: This product doesn't have in this event!", 'danger');
        }
    }
    onChangeDiscount(id, val) {
        let index = this.listDiscountProductModel.findIndex(x => x.productId == id);
        this.listDiscountProductModel[index].discount = parseInt(val);
    }
    toDate(dateStr) {
        var parts = dateStr.split("/");
        return new Date(parts[2], parts[1] - 1, parts[0]);
    }
    EditEvent(data: any) {

        let eventModel = new InsertEventModel();
        eventModel.id = data.id;
        eventModel.eventName = data.eventName;
        eventModel.eventPictureUrl = data.eventPictureUrl;
        eventModel.fromDate=this.toDate(data.fromDate);
        eventModel.toDate=this.toDate(data.toDate);
        eventModel.discountProducts = this.listDiscountProductModel;

        this.eventService.UpdateEvent(eventModel.id,eventModel).subscribe((response: any) => {
            swal(
                'Success',
                'Your item have been updated.',
                'success'
            );
            this.router.navigate(['/dashboard/event']);
        }, (error: any) => {
            swal(
                'Error',
                'Some error occurred,pls try again latter !',
                'error'
            )
        });
    }

    async onChangeUrl() {
        this.subscription = this.activeRoute.params.subscribe(params => {
            this.id = params['id'];
        });
        await this.eventService.GetEventByID(this.id).toPromise().then((response) => {
            response.fromDate = new Date(response.fromDate).toLocaleDateString();
            response.toDate = new Date(response.toDate).toLocaleDateString();
            this.event = response;

            this.discountProducts = response.discountProducts;

            this.filterArray(this.discountProducts);
        })
        await $.getScript('../../../../assets/js/plugins/bootstrap-datetimepicker.js');
        await initDatetimepickers();

        //dung js set value cho cai 

    }

    filterArray(array: any[]) {
        array.forEach(element => {
            var model = new DiscountProductModel();
            model.productId = element.productId.id;
            model.productName = element.productId.productName;
            model.productPrice = element.productId.productPrice;
            model.discount = element.discount;
            //console.log(element);
            this.listDiscountProductModel.push(model);
            //console.log(this.listDiscountProductModel);
        });
    }
    backClick() {
        this._location.back();
    }

    ngOnInit() {

        //$.getScript('../../../assets/js/init/initDataTable.js');

    }

}