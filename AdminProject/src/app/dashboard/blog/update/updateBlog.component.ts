import { Component, OnInit, ElementRef } from '@angular/core';
import { Location } from '@angular/common';
import { Router, ActivatedRoute, NavigationEnd } from '@angular/router';
import { Subscription } from 'rxjs';
import 'rxjs/add/operator/filter';

import { BlogService } from '../../../service/BlogService/blog.service';

declare var $: any;
declare var swal: any;

@Component({
    moduleId: module.id,
    selector: 'app-updateBlog',
    templateUrl: 'updateBlog.component.html',
    providers: [BlogService]
})

export class UpdateBlogComponent implements OnInit {

    public blog: any;
    public subscription: Subscription;
    public id: string;
    constructor(private el: ElementRef, private _location: Location,
        private blogService: BlogService, private router: Router,
        private activeRoute: ActivatedRoute) {
        //Code constructor
        router.events.filter(event => event instanceof NavigationEnd).subscribe((val) => {
            this.onChangeUrl();
        });
    }
    ngOnInit() {

    }
    backClick() {
        this._location.back();
    }
    onChangeUrl() {
        this.subscription = this.activeRoute.params.subscribe(params => {
            this.id = params['id'];
        });
        this.blogService.GetBlogById(this.id).subscribe((response: any) => {
            this.blog = response;
        });
    }
    EditBlog(data:any){
        this.blogService.UpdateBlog(data.id,data).subscribe(()=>{
            swal(
                'Success',
                'Your item have been updated.',
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