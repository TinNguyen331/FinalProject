import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Location} from '@angular/common';

declare var $: any;
declare var swal: any;

@Component({
    moduleId: module.id,
    selector: 'app-updateOrder',
    templateUrl: 'viewOrder.component.html',
})

export class ViewOrderComponent implements OnInit {
    
    order:any;
    listProduct:any[]=[];
    constructor(private route: ActivatedRoute,private _location:Location){}

    ngOnInit() {
       this.order=this.route.snapshot.data['order'];
       this.listProduct=this.order.details;
       console.log(this.order)
       console.log(this.listProduct);
    }
    backClick(){
        this._location.back();
    }
}