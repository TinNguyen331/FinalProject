import { Component, OnInit } from '@angular/core';
import { UserService } from '../../service/UserService/user.service';

declare var $: any;
declare var swal: any;

@Component({
    moduleId: module.id,
    selector: 'app-user',
    templateUrl: 'user.component.html',
    providers:[UserService]
})

export class UserComponent implements OnInit {
    
    public listUser:any[];
    constructor(private userService:UserService) {
        this.LoadData();
    }
    LoadData() {
        this.userService.GetAllUser().subscribe((response:any)=>{
            this.listUser=response;
           //console.log(this.listUser);
            $.getScript('../../../assets/js/init/initDataTable.js');
        })
    }
    DeleteUser(data:string) {
       
        swal({
            title: 'Are you sure?',
            text: 'You will not be able to recover this Item !',
            type: 'warning',
            showCancelButton: true,
            confirmButtonText: 'Yes, delete it!',
            cancelButtonText: 'No, keep it'
        }).then(() => {
            this.userService.DeleteUser(data).subscribe(()=>{
                this.LoadData();
            })
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