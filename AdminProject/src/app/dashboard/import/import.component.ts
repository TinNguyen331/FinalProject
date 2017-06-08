import { Component, OnInit } from '@angular/core';
import { PriceByDayService } from '../../service/PriceByDayService/pricebyday.service';
import { Import } from './importModel';

declare var $: any;
declare var swal: any;

@Component({
    moduleId: module.id,
    selector: 'app-import',
    templateUrl: 'import.component.html',
})

export class ImportComponent implements OnInit {

    ngOnInit() {
        $.getScript('../../../assets/js/init/initDataTable.js');
    }
}