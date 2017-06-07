import { Component, OnInit } from '@angular/core';
import { BlogService } from '../../service/BlogService/blog.service';

declare var $: any;
declare var swal: any;

@Component({
    moduleId: module.id,
    selector: 'app-blog',
    templateUrl: 'blog.component.html',
    providers:[BlogService]
})

export class BlogComponent implements OnInit {

    public listBlog:any[];
    constructor(private blogService:BlogService){}

    ngOnInit() {
        
        this.loadData();
    }
    loadData(){
        this.blogService.GetAllBlog().subscribe((response:any)=>{
            this.listBlog=response;
            $.getScript('../../../assets/js/init/initDataTable.js');
        });
    }
}