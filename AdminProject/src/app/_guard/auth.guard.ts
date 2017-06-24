import { Injectable } from '@angular/core';
import { Router, ActivatedRouteSnapshot, CanActivate, RouterStateSnapshot } from '@angular/router';
import { AuthenticationService } from '../service/AuthenticationService/authentication.service';

@Injectable()
export class AuthGuard implements CanActivate {
    constructor(private auth: AuthenticationService, private router: Router) { }

    canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        if (this.auth.loggedIn()) {
            return true;
        }
        this.router.navigate(['']);
        return false;
    }
}