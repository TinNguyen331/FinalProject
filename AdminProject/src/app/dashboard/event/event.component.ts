import { Component, OnInit } from '@angular/core';
import { EventService } from '../../service/eventService/event.service';
import { DatePipe } from '@angular/common';

declare var $: any;
declare var swal: any;

@Component({
    moduleId: module.id,
    selector: 'app-event',
    templateUrl: 'event.component.html',
    providers: [EventService,DatePipe]
})

export class EventComponent implements OnInit {

    public listEvent: any[];
    constructor(private eventService: EventService,private datePipe: DatePipe) {
        this.LoadData();
    }
    LoadData() {
        this.eventService.GetAllEvent().subscribe((response: any) => {
            this.listEvent = response;
            console.log(this.listEvent);
            $.getScript('../../../assets/js/init/initDataTable.js');
        });
    }
    convertDate(date):string{
        if(date==null)
        return '';
        else
        return this.datePipe.transform(date);
    }
    ngOnInit() {

    }
}