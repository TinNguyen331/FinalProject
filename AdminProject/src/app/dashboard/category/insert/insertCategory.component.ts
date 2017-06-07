import { Component, OnInit } from '@angular/core';
import { Location} from '@angular/common';
import { Router } from '@angular/router';
import { Subscription }   from 'rxjs/Subscription';

import { CategoryService } from '../../../service/CategoryService/category.service';

declare var $: any;
declare var swal: any;

@Component({
    moduleId: module.id,
    selector: 'app-insertCategory',
    templateUrl: 'insertCategory.component.html',
    providers:[CategoryService]
})

export class InsertCategoryComponent implements  OnInit{
    constructor(private _location:Location,private cateService:CategoryService,
    private router:Router){

    }
    backClick(){
        this._location.back();
    }
    AddNew(data:any){
        this.cateService.AddNewCategory(data).subscribe((response:any)=>{
            swal(
                'Success',
                'Your item have been added.',
                'success'
            );
            this.router.navigate(['/dashboard/category']);
        },(error:any)=>{
            swal(
                'Error',
                'Some error occurred,pls try again latter !',
                'error'
            )
        });
    }
    ngOnInit(){
        
    }
}