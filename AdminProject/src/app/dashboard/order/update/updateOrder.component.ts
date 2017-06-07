import { Component, OnInit } from '@angular/core';
//import initSliders = require('../../../../assets/js/init/initSliders.js');
import initDatetimepickers = require('../../../../assets/js/init/initDatetimepickers.js');


declare var $: any;
declare var swal: any;

@Component({
    moduleId: module.id,
    selector: 'app-updateOrder',
    templateUrl: 'updateOrder.component.html',
})

export class UpdateOrderComponent implements OnInit {
    ngOnInit() {
        $.getScript('../../../assets/js/plugins/bootstrap-datetimepicker.js');
        $.getScript('../../../assets/js/plugins/jquery.tagsinput.js');

        if ($(".selectpicker").length != 0) {
            $(".selectpicker").selectpicker();
        }

        initDatetimepickers();
        //initSliders();
    }
}