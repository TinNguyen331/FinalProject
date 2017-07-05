import { Component, OnInit } from '@angular/core';
import { ProductService } from '../../service/ProductService/product.service';

declare var $: any;
declare var swal: any;


@Component({
    moduleId: module.id,
    selector: 'app-product',
    templateUrl: 'product.component.html',
    providers:[ProductService]
})

export class ProductComponent implements OnInit {
    
    public listPro:any;
    constructor(private productService:ProductService) {
        this.LoadData();
        
    }
    
    LoadData() {
        this.productService.GetAllProduct().subscribe((response:any)=>{
            this.listPro=response;
            $.getScript('../../../assets/js/init/initDataTable.js');
        })
    }
    LoadDelete(){
        this.productService.GetAllProduct().subscribe((response:any)=>{
            this.listPro=response;
        })
    }
    DeleteProduct(data: string) {
        swal({
            title: 'Are you sure?',
            text: 'You will not be able to recover this Item !',
            type: 'warning',
            showCancelButton: true,
            confirmButtonText: 'Yes, delete it!',
            cancelButtonText: 'No, keep it'
        }).then(() => {
            this.productService.DeleteProduct(data).subscribe((response: any) => {
                this.LoadDelete();
            });
            swal(
                'Deleted!',
                'Your Item has been deleted.',
                'success'
            );
        }, (dismiss) => {
            // dismiss can be 'overlay', 'cancel', 'close', 'esc', 'timer'
            if (dismiss === 'cancel') {
                swal(
                    'Cancelled',
                    'Your Item is safe :)',
                    'error'
                )
            }
        });

    }


    ngOnInit() {
        
    }
}