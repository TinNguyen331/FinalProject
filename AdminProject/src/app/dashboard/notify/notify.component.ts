import { Component, OnInit } from '@angular/core';


declare var $: any;
declare var swal: any;

@Component({
    moduleId: module.id,
    selector: 'app-notify',
    templateUrl: 'notify.component.html',
})

export class NotifyComponent implements OnInit {
    ngOnInit() {
        $.getScript('../../../assets/js/init/initDataTable.js');
    }
}