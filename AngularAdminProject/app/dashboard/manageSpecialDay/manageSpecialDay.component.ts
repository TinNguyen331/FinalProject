import { Component, OnInit, trigger, state, style, transition, animate } from '@angular/core';

declare var $:any;

@Component({
    selector: 'manageSpecialDay-cmp',
    moduleId: module.id,
    templateUrl: 'manageSpecialDay.component.html'
})

export class ManageSpecialDayComponent implements OnInit{
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
