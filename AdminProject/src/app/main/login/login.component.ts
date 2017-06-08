import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { AuthenticationService } from '../../service/AuthenticationService/authentication.service';

import { tokenNotExpired, JwtHelper } from 'angular2-jwt';

declare var $: any;
declare var type: any;
declare var color: any;

@Component({
    moduleId: module.id,
    selector: 'login',
    templateUrl: 'login.component.html',
    providers: [AuthenticationService]
})

export class LoginComponent implements OnInit {
    returnUrl: string;
    jwtHelper: JwtHelper = new JwtHelper();
    constructor(private authenticationService: AuthenticationService, private route: ActivatedRoute,
        private router: Router) { }

    ngOnInit() {
        // reset login status
        //this.authenticationService.logout();
    }

    doLogin(data: any) {
        let flag: boolean = false;
        this.authenticationService.doLogin(data).subscribe((respose: any) => {
            if (respose === true) {
                this.router.navigate(['/dashboard'])
            }
            else{
                this.Notify('top', 'center', "Error: You don't have permission to access this site", 'danger');
            }
        }, (error: any) => {
            this.Notify('top', 'center', 'Error: Username or password is incorrect', 'danger');
        });

    }
    Notify(from: any, algin: string, message: string, type: string): void {
        console.log("Call notify");
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