import { Component, OnInit } from '@angular/core';

import { OrderService } from '../../service/OrderService/order.service';

declare var $: any;
declare var swal: any;

@Component({
    moduleId: module.id,
    selector: 'app-order',
    templateUrl: 'order.component.html',
    providers:[OrderService]
})

export class OrderComponent implements OnInit {

    listNewOrder:any[]=[];
    listSendingOrder:any[]=[];
    listCompletedOrder:any[]=[];


    constructor(private orderService:OrderService){
       this.prepareData();
       $.getScript('../../../assets/js/init/initDataTable.js');
    }

    ngOnInit() {
        
    }
    addToSending(id:string){
        this.orderService.AddToSending(id).subscribe(()=>{
            swal(
                'Success',
                'Order have been delivery',
                'success'
            );
            this.prepareData();
        },((error)=>{
            swal(
                'Error',
                'Some error occurred,pls try again latter !',
                'error'
            )
        }));
    }
    addToDelivery(id:string){
        this.orderService.AddToDelivery(id).subscribe(()=>{
            swal(
                'Success',
                'Your Order have been add to Success',
                'success'
            );
            this.prepareData();
        },((error)=>{
            swal(
                'Error',
                'Some error occurred,pls try again latter !',
                'error'
            )
        }));
    }
    deleteOrder(id:string){
        swal({
            title: 'Are you sure?',
            text: 'You will not be able to recover this Order !',
            type: 'warning',
            showCancelButton: true,
            confirmButtonText: 'Yes, delete it!',
            cancelButtonText: 'No, keep it'
        }).then(() => {
            this.orderService.DeleteOrder(id).subscribe((response: any) => {
                this.prepareData();
            },()=>{
                swal(
                'Error',
                'Order already deleted !',
                'error'
            )
            });
            swal(
                'Deleted!',
                'Order has been deleted.',
                'success'
            );
        }, (dismiss) => {
            // dismiss can be 'overlay', 'cancel', 'close', 'esc', 'timer'
            if (dismiss === 'cancel') {
                swal(
                    'Cancelled',
                    'Order is safe :)',
                    'error'
                )
            }
        });
    }

     prepareData(){
         this.orderService.GetAllNewOrder().subscribe((data:any)=>{
            this.listNewOrder=data;
        });
        this.orderService.GetAllSendingOrder().subscribe((data:any)=>{
            this.listSendingOrder=data;
        });
         this.orderService.GetAllCompletedOrder().subscribe((data:any)=>{
            this.listCompletedOrder=data;
        });
        
    }
}