import { Component, OnInit, trigger, state, style, transition, animate } from '@angular/core';

declare var $:any;

@Component({
    selector: 'manageProduct-cmp',
    moduleId: module.id,
    templateUrl: 'manageProduct.component.html'
})

export class ManageProductComponent implements OnInit{
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
