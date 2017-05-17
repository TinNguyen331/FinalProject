import { Component, OnInit, trigger, state, style, transition, animate } from '@angular/core';


declare var $:any;

@Component({
    selector: 'manageNotify-cmp',
    moduleId: module.id,
    templateUrl: 'manageNotify.component.html'
})

export class ManageNotifyComponent implements OnInit{
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
