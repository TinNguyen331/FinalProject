import { Component, OnInit,ElementRef } from '@angular/core';
import { Location } from '@angular/common';
import { Router, ActivatedRoute, NavigationEnd } from '@angular/router';
import { Subscription } from 'rxjs';
import 'rxjs/add/operator/filter';

import { MeaningService } from '../../../service/MeaningService/meaning.service';

declare var $: any;
declare var swal: any;

@Component({
    moduleId: module.id,
    selector: 'app-updateMeaning',
    templateUrl: 'updateMeaning.component.html',
    providers:[MeaningService]
})

export class UpdateMeaningComponent implements OnInit {

    public meaning: any;
    public subscription: Subscription;
    public id: string;
    constructor(private el: ElementRef, private _location: Location,
        private meaningService: MeaningService, private router: Router,
        private activeRoute: ActivatedRoute) {
        //Code constructor
        router.events.filter(event => event instanceof NavigationEnd).subscribe((val) => {
            this.onChangeUrl();
        });
    }
    backClick() {
        this._location.back();
    }
    onChangeUrl() {
        this.subscription = this.activeRoute.params.subscribe(params => {
            this.id = params['id'];
        });
        this.meaningService.GetMeaningById(this.id).subscribe((response: any) => {
            this.meaning = response;
        });
    }
    EditBlog(data:any){
        this.meaningService.UpdateMeaning(data.id,data).subscribe(()=>{
            swal(
                'Success',
                'Your item have been updated.',
                'success'
            );
            this.router.navigate(['/dashboard/meaning']);
        },error=>{
             swal(
                'Error',
                'Some error occurred,pls try again latter !',
                'error'
            )
        });
    }
    ngOnInit() {
       
    }
}