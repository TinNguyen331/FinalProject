import { Component, OnInit } from '@angular/core';


declare var $: any;
declare var swal: any;

@Component({
    moduleId: module.id,
    selector: 'app-meaning',
    templateUrl: 'meaning.component.html',
})

export class MeaningComponent implements OnInit {
constructor() {
        
    }
    LoadData() {

    }
    Delete() {

    }
    DeleteCategory() {
        console.log();//log ra dc
        swal({
            title: 'Are you sure?',
            text: 'Do you want to delete this product item?',
            type: 'warning',
            showCancelButton: true,
            confirmButtonText: 'Yes, delete it!',
            cancelButtonText: 'No, keep it'
        }).then(() => {
            swal(
                'Changed!',
                'Your Item has been deleted.',
                'success'
            );
        }, (dismiss) => {
            // dismiss can be 'overlay', 'cancel', 'close', 'esc', 'timer'
            if (dismiss === 'cancel') {
                swal(
                    'Cancelled',
                    'Your Item is safe :)',
                    'error'
                )
            }
        });

    }
    ngOnInit() {
        $.getScript('../../../assets/js/init/initDataTable.js');
    }
}