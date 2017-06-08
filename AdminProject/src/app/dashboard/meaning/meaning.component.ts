import { Component, OnInit } from '@angular/core';

import { MeaningService } from '../../service/MeaningService/meaning.service';
declare var $: any;
declare var swal: any;

@Component({
    moduleId: module.id,
    selector: 'app-meaning',
    templateUrl: 'meaning.component.html',
    providers:[MeaningService]
})

export class MeaningComponent implements OnInit {
    
    listMeaning:any[];
    constructor(private meaningService:MeaningService) {
        this.loadData();
    }
    loadData(){
        this.meaningService.GetAllMeaning().subscribe((response:any)=>{
            this.listMeaning=response;
            $.getScript('../../../assets/js/init/initDataTable.js');
        });
    }
    
    ngOnInit() {
        
    }
}