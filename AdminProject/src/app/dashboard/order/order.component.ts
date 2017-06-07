import { Component, OnInit } from '@angular/core';


declare var $: any;
declare var swal: any;

@Component({
    moduleId: module.id,
    selector: 'app-order',
    templateUrl: 'order.component.html',
})

export class OrderComponent implements OnInit {
    ngOnInit() {
        $.getScript('../../../assets/js/init/initDataTable.js');
    }
}