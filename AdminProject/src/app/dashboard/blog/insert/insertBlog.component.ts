import { Component, OnInit } from '@angular/core';
import { Location} from '@angular/common';
import { Router } from '@angular/router';
import { BlogService } from '../../../service/BlogService/blog.service';

declare var $: any;
declare var swal: any;

@Component({
    moduleId: module.id,
    selector: 'app-insertBlog',
    templateUrl: 'insertBlog.component.html',
    providers:[BlogService]
})

export class InsertBlogComponent implements OnInit {

    constructor(private _location:Location,private blogService:BlogService,
    private router:Router){}

    ngOnInit() {    
       
    }
    backClick(){
        this._location.back();
    }
    AddBlog(data:any){
        console.log(data);
        this.blogService.AddNewBlog(data).subscribe(()=>{
            swal(
                'Success',
                'Your item have been added.',
                'success'
            );
            this.router.navigate(['/dashboard/blog']);
        },error=>{
             swal(
                'Error',
                'Some error occurred,pls try again latter !',
                'error'
            )
        });
    }
}