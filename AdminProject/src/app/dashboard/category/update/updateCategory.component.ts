import { Component, OnInit,ElementRef } from '@angular/core';
import { Location } from '@angular/common';
import { Router, ActivatedRoute, NavigationEnd } from '@angular/router';
import { Subscription } from 'rxjs';
import 'rxjs/add/operator/filter';

import { CategoryService } from '../../../service/CategoryService/category.service';

declare var $: any;
declare var swal: any;

@Component({
    moduleId: module.id,
    selector: 'app-updateCategory',
    templateUrl: 'updateCategory.component.html',
    providers:[CategoryService]
})

export class UpdateCategoryComponent implements OnInit {
    public cate:any;
    public subscription: Subscription;
    public id:string;

    constructor(private el:ElementRef,private _location:Location,
    private categoryService: CategoryService,private router:Router,
    private activeRoute:ActivatedRoute){
         //Code constructor
        router.events.filter(event=>event instanceof NavigationEnd).subscribe((val)=>{
            this.onChangeUrl();
        });
    }

    backClick(){
        this._location.back();
    }
    Edit(data:any){
        this.subscription=this.categoryService.UpdateCategory(data.id,data).subscribe((response:any)=>{
            swal(
                'Success',
                'Your item have been updated.',
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
    onChangeUrl(){
         this.subscription=this.activeRoute.params.subscribe(params=>{
             this.id=params['id'];
         });
         this.categoryService.GetCategoryById(this.id).subscribe((response:any)=>{
             this.cate=response;
         });
     }
     ngOnInit(){}
  
    
}