import { Component, OnInit } from '@angular/core';
import initFullCalendar = require('../../../assets/js/init/initFullCalendar.js');


declare var $: any;
declare var swal: any;

@Component({
    moduleId: module.id,
    selector: 'app-specialday',
    templateUrl: 'specialday.component.html',
})

export class SpecialDayComponent implements OnInit {

    ngOnInit() {
        $.getScript('../../../assets/js/init/initDataTable.js');
        initFullCalendar();
    }
}