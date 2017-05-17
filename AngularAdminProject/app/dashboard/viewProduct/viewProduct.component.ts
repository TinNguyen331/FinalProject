import { Component, OnInit, trigger, state, style, transition, animate } from '@angular/core';

declare var $:any;

@Component({
    selector: 'viewProduct-cmp',
    moduleId: module.id,
    templateUrl: 'viewProduct.component.html'
})

export class ViewProductComponent implements OnInit{
    ngOnInit(){
        // $('[data-toggle="checkbox"]').each(function () {
        //     if($(this).data('toggle') == 'switch') return;
        //
        //     var $checkbox = $(this);
        //     $checkbox.checkbox();
        // });
    }
}
