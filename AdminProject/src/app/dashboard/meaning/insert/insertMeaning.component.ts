import { Component, OnInit } from '@angular/core';
import { Location} from '@angular/common';
import { Router } from '@angular/router';
import { MeaningService } from '../../../service/MeaningService/meaning.service';

declare var $: any;
declare var swal: any;

@Component({
    moduleId: module.id,
    selector: 'app-insertMeaning',
    templateUrl: 'insertMeaning.component.html',
    providers:[MeaningService]
})

export class InsertMeaningComponent implements OnInit {

    constructor(private _location:Location,private meaningService:MeaningService,
    private router:Router){}

    ngOnInit() {
       
    }
    AddMeaning(data:any){
        console.log(data);
        this.meaningService.AddNewMeaning(data).subscribe(()=>{
            swal(
                'Success',
                'Your item have been added.',
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
    backClick(){
        this._location.back();
    }
}