import { Component, OnInit, trigger, state, style, transition, animate } from '@angular/core';

declare var $:any;

@Component({
    selector: 'manageCategory-cmp',
    moduleId: module.id,
    templateUrl: 'manageCategory.component.html'
})

export class ManageCategoryComponent implements OnInit{
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
