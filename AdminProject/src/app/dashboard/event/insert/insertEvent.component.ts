import { Component, OnInit } from '@angular/core';


declare var $: any;
declare var swal: any;

@Component({
    moduleId: module.id,
    selector: 'app-insertEvent',
    templateUrl: 'insertEvent.component.html',
})

export class InsertEventComponent implements OnInit {
    
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
            text: 'You will not be able to recover this Item !',
            type: 'warning',
            showCancelButton: true,
            confirmButtonText: 'Yes, delete it!',
            cancelButtonText: 'No, keep it'
        }).then(() => {
            swal(
                'Deleted!',
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
       
    }
}