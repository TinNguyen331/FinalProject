import { Component, OnInit } from '@angular/core';
import { DatePipe } from '@angular/common';
import { OrderService } from '../../service/OrderService/order.service';

declare var $: any;
declare var swal: any;

@Component({
    moduleId: module.id,
    selector: 'app-order',
    templateUrl: 'order.component.html',
    providers:[OrderService,DatePipe]
})

export class OrderComponent implements OnInit {

    listNewOrder:any[]=[];
    listSendingOrder:any[]=[];
    listCompletedOrder:any[]=[];


    constructor(private orderService:OrderService,private datePipe:DatePipe){
       this.prepareData();
       
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
            this.prepareDataDelete();
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
            this.prepareDataDelete();
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
                this.prepareDataDelete();
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

    async prepareDataDelete(){
        await this.orderService.GetAllNewOrder().subscribe((data:any)=>{
            this.listNewOrder=data;
            
        });
        await this.orderService.GetAllSendingOrder().subscribe((data:any)=>{
            this.listSendingOrder=data;
            
        });
        await this.orderService.GetAllCompletedOrder().subscribe((data:any)=>{
            this.listCompletedOrder=data;
            
        });
    }
    
    async prepareData(){
        await this.orderService.GetAllNewOrder().subscribe((data:any)=>{
            this.listNewOrder=data;
            
        });
        await this.orderService.GetAllSendingOrder().subscribe((data:any)=>{
            this.listSendingOrder=data;
            
        });
        await this.orderService.GetAllCompletedOrder().subscribe((data:any)=>{
            this.listCompletedOrder=data;
            $.getScript('../../../assets/js/init/initDataTable.js');
        });
       
    }

    convertDate(date):string{
        if(date==null)
        return '';
        else
        return this.datePipe.transform(date);
    }
}