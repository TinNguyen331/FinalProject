import { Component, OnInit } from '@angular/core';
import { Location } from '@angular/common';
import { UserService } from '../../../service/UserService/user.service';
import { Router } from '@angular/router';

declare var $: any;
declare var swal: any;

@Component({
  selector: 'app-home',
  templateUrl: './changepassword.component.html',
  providers: [UserService]

})
export class ChangePasswordComponent implements OnInit {

  constructor(private router:Router,private _location:Location,private userService: UserService) {

  }

  changePass(data: any) {
    let resultCompare = data.newPass === data.verifyNewPass;
    if (resultCompare == false) {
      this.userService.UpdatePassWord(data);
      this.Notify('top', 'center', "Error: Password New and Re-Password New does not match !", 'danger');
    }
    else {
      this.userService.UpdatePassWord(data).subscribe((data) => {
        swal(
          'Success',
          'Your password have been changed.',
          'success'
        );
        this.router.navigate(['/dashboard/account']);
      }, ((error) => {
        this.Notify('top', 'center', "Error: Password present does not match !", 'danger');
      }));
    }
  }
  ngOnInit() {

  }
  backClick(){
    this.router.navigate(['/dashboard/account']);
  }
  Notify(from: any, algin: string, message: string, type: string): void {
    //type = ['', 'info', 'success', 'warning', 'danger', 'rose', 'primary'];
    //color = Math.floor((Math.random() * 6) + 1);
    $.notify({
      icon: "notifications",
      message: message

    }, {
        type: type,
        timer: 30,
        placement: {
          from: from,
          align: algin
        }
      });
  }

}
