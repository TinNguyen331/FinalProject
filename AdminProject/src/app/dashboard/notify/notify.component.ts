import { Component, OnInit } from '@angular/core';
import { NotifyService } from '../../service/NotifyService/notify.service'; 

declare var $: any;
declare var swal: any;

@Component({
    moduleId: module.id,
    selector: 'app-notify',
    templateUrl: 'notify.component.html',
    providers:[NotifyService]
})

export class NotifyComponent implements OnInit {

    listNotify:any[];
    constructor(private notifyService:NotifyService){}
    ngOnInit() {
        this.loadData();
    }

    loadData(){
        this.notifyService.GetAllNotify().subscribe((response:any)=>{
            this.listNotify=response;
            $.getScript('../../../assets/js/init/initDataTable.js');
        })
    }
}