import { Component, OnInit } from '@angular/core';
import { PriceByDayService } from '../../service/PriceByDayService/pricebyday.service'; 
import { pricebyday } from './pricebydayModel';

declare var $: any;
declare var swal: any;

@Component({
    moduleId: module.id,
    selector: 'app-pricebyday',
    templateUrl: 'pricebyday.component.html',
    providers:[PriceByDayService]
})

export class PriceByDayComponent implements OnInit {

    public requestData:pricebyday;
    public listPrice:any[];
    constructor(private priceByDayService:PriceByDayService) {
        this.LoadData();
        this.requestData=new pricebyday();
    }
    LoadData() {
        this.priceByDayService.GetAllPriceByDay().subscribe((response:any)=>{
            this.listPrice=response;
            //console.log(this.listPrice);
            $.getScript('../../../assets/js/init/initDataTable.js');
        });
    }
    UpdatePriceByDay(newPrice:number,productId:String) {
        //console.log(newPrice);
        //console.log(productId);
        
        this.requestData.productId=productId;
        this.requestData.price=newPrice;
        //console.log(this.requestData);
        swal({
            title: 'Are you sure?',
            text: 'Do you want to update this product price?',
            type: 'warning',
            showCancelButton: true,
            confirmButtonText: 'Yes, update it!',
            cancelButtonText: 'No, keep it'
        }).then(() => {
            this.priceByDayService.AddNewPriceByDay(this.requestData).subscribe(()=>{
                console.log("Update Success");
                 this.LoadData();
            });
            swal(
                'Changed!',
                'Your Item has been updated a price.',
                'success'
            );
           
        }, (dismiss) => {
            // dismiss can be 'overlay', 'cancel', 'close', 'esc', 'timer'
            if (dismiss === 'cancel') {
                swal(
                    'Cancelled',
                    'Your Item is not change :)',
                    'error'
                )
            }
        });
     
    }


    ngOnInit() {
        
    }
}