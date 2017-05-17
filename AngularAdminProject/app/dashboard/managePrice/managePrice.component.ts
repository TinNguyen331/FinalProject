import { Component, OnInit, trigger, state, style, transition, animate } from '@angular/core';

declare var $:any;

@Component({
    selector: 'managePrice-cmp',
    moduleId: module.id,
    templateUrl: 'managePrice.component.html'
})

export class ManagePriceComponent implements OnInit{
    ngOnInit(){
        // $('[data-toggle="checkbox"]').each(function () {
        //     if($(this).data('toggle') == 'switch') return;
        //
        //     var $checkbox = $(this);
        //     $checkbox.checkbox();
        // });
        $.getScript('./assets/js/init/initDataTable.js');
    }
}
