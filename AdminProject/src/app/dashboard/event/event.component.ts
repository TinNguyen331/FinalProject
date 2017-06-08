import { Component, OnInit } from '@angular/core';
import { EventService } from '../../service/eventService/event.service';

declare var $: any;
declare var swal: any;

@Component({
    moduleId: module.id,
    selector: 'app-event',
    templateUrl: 'event.component.html',
    providers: [EventService]
})

export class EventComponent implements OnInit {

    public listEvent: any[];
    constructor(private eventService: EventService) {
        this.LoadData();
    }
    LoadData() {
        this.eventService.GetAllEvent().subscribe((response: any) => {
            this.listEvent = response;
            console.log(this.listEvent);
            $.getScript('../../../assets/js/init/initDataTable.js');
        });
    }
    
    ngOnInit() {

    }
}