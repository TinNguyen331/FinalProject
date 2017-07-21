import { Component, OnInit } from '@angular/core';
import { Location } from '@angular/common';
import initDatetimepickers = require('../../../../assets/js/init/initDatetimepickers.js');
import { ProductService } from '../../../service/ProductService/product.service';
import { EventService } from '../../../service/eventService/event.service';
import { EventModel } from '../event.model';
import { ProductInEventModel } from '../productInEvent.model';
import { InsertEventModel } from '../insertEvent.model';
import { Router } from '@angular/router';

declare var $: any;
declare var swal: any;

@Component({
    moduleId: module.id,
    selector: 'app-insertEvent',
    templateUrl: 'insertEvent.component.html',
    providers: [ProductService, EventService]
})

export class InsertEventComponent implements OnInit {

    public listPro: any[];
    public listProductInEvent: EventModel[] = [];
    public listProductInEventInsert: ProductInEventModel[] = [];

    constructor(private _location: Location, private productService: ProductService,
        private eventService: EventService, private router: Router) {
        this.LoadProduct();
    }

    LoadProduct() {
        this.productService.GetAllProduct().subscribe((response: any) => {
            this.listPro = response;
            $.getScript('../../../assets/js/init/initDataTable.js');
        })
    }

    backClick() {
        this._location.back();
    }

    checkIdExits(id: string) {
        let index = this.listProductInEvent.findIndex(x => x.productId == id);
        if (index > -1) {
            return true;
        }
        return false;
    }

    checkIdInsertExits(id: string) {
        let index = this.listProductInEventInsert.findIndex(x => x.productId == id);
        if (index > -1) {
            return true;
        }
        return false;
    }

    addProInEvent(id: string, productName: string, productPrice: number) {
        // console.log(id);
        // console.log(productName);
        // console.log(productPrice);

        if (this.checkIdExits(id)) {
            this.Notify('top', 'center', "Error: This product has been selected in this event!", 'danger');
        }
        else {
            let event = new EventModel();
            event.productId = id;
            event.productName = productName;
            event.productPrice = productPrice;
            this.listProductInEvent.push(event);
            // console.log(this.listProductInEvent);
        }
    }

    Notify(from: any, algin: string, message: string, type: string): void {
        //console.log("Call notify");
        //type = ['', 'info', 'success', 'warning', 'danger', 'rose', 'primary'];
        //color = Math.floor((Math.random() * 6) + 1);
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

    removeProductInEvent(id: string) {
        // console.log(id);
        // console.log("Prepare remove");
        // console.log(this.listProductInEvent);
        if (this.checkIdExits(id)) {
            //console.log("in")
            let index = this.listProductInEvent.findIndex(x => x.productId == id)
            this.listProductInEvent.splice(index, 1);
            // console.log("done");
            // console.log(this.listProductInEvent);
        }
        else {
            this.Notify('top', 'center', "Error: This product doesn't have in this event!", 'danger');
        }
    }

    onChangeDiscount(id: string, val: string) {

        if (!this.checkIdInsertExits(id)) {
            console.log("insert");
            let proInEvent = new ProductInEventModel();
            proInEvent.productId = id;
            proInEvent.discount = parseInt(val);

            this.listProductInEventInsert.push(proInEvent);
        }
        else {
            console.log("id exits");
            let index = this.listProductInEventInsert.findIndex(x => x.productId == id);
            this.listProductInEventInsert[index].discount = parseInt(val);
        }
        console.log("listproductinevent insert");
        console.log(this.listProductInEventInsert);
    }

    AddNewEvent(data: any, formDate, toDate) {

        let errorMessage=this.validateModel(data,formDate,toDate);
        if (errorMessage != ""){
            this.Notify('top', 'center', errorMessage, 'danger');
            return void 0;
        }
        let listEventComplete = new InsertEventModel();
        listEventComplete.eventName = data.eventName;
        listEventComplete.eventPictureUrl = data.eventPictureUrl;
        listEventComplete.eventMobilePictureUrl=data.eventMobilePictureUrl;
        listEventComplete.fromDate = new Date(formDate);
        listEventComplete.toDate = new Date(toDate);
        listEventComplete.discountProducts = this.listProductInEventInsert;

        this.eventService.AddNewEvent(listEventComplete).subscribe((response: any) => {
            swal(
                'Success',
                'Your item have been added.',
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
    ngOnInit() {
        $.getScript('../../../../assets/js/plugins/bootstrap-datetimepicker.js');
        //$.getScript('../../../assets/js/init/initDataTable.js');
        initDatetimepickers();

        // $('.datepicker').datepicker({
        // }).datepicker("setDate", new Date());
    }

    validateModel(data: any, fromDate, toDate): string {
        console.log("data");
        console.log(data)
        let errorMessage = "";
        if (data.eventName == "") {
            errorMessage = "Event name is required";
            return errorMessage;
        }
        if (data.eventPictureUrl == "") {
            errorMessage = "Event picture is required";
            return errorMessage;
        }
        if (data.eventMobilePictureUrl == "") {
            errorMessage = "Event mobile picture is required";
            return errorMessage;
        }
        if (fromDate == "") {
            errorMessage = "From date is required";
            return errorMessage;
        }
        if (toDate == "") {
            errorMessage = "To date is required";
            return errorMessage;
        }
        if (fromDate > toDate) {
            errorMessage = "To date much be greater than from date";
            return errorMessage;
        }
        if (this.listProductInEventInsert.length == 0) {
            errorMessage = "You much be fill discount percent for product";
            return errorMessage;
        } else {
            this.listProductInEventInsert.forEach((value) => {
                if (Number.isNaN(value.discount)) {
                    let product = this.listPro.find(x => x.id == value.productId);
                    errorMessage = "You much be fill discount percent for product: " + product.productName;
                    return errorMessage;
                }
            })
        }

        return errorMessage;
    }
}