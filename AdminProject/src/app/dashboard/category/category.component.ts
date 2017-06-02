import { Component, OnInit } from '@angular/core';
import { CategoryService } from '../../service/CategoryService/category.service';


declare var $: any;
declare var swal: any;

@Component({
    moduleId: module.id,
    selector: 'app-category',
    templateUrl: 'category.component.html',
    providers: [CategoryService]
})

export class CategoryComponent implements OnInit {

    public listCate: any[];
    constructor(private categoryService: CategoryService) {
        this.LoadData();
    }
    LoadData() {
        this.categoryService.GetAllCategory().subscribe((response: any) => {
            this.listCate = response;
        });
    }
    Delete(data: string) {

    }
    DeleteCategory(data: string) {
        console.log(data);//log ra dc
        swal({
            title: 'Are you sure?',
            text: 'You will not be able to recover this Item !',
            type: 'warning',
            showCancelButton: true,
            confirmButtonText: 'Yes, delete it!',
            cancelButtonText: 'No, keep it'
        }).then(() => {
            this.categoryService.DeleteCategory(data).subscribe((response: any) => {
                this.LoadData();
            });
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
        $.getScript('../../../assets/js/init/initDataTable.js');
    }
}