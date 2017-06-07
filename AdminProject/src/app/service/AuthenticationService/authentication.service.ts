import { Injectable } from '@angular/core';
import { Http, Response, Headers, RequestOptions } from '@angular/http';
import { Observable } from 'rxjs/Observable';
import { tokenNotExpired, JwtHelper } from 'angular2-jwt';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/catch';

@Injectable()
export class AuthenticationService {

    private apiUrl = "http://localhost:8080/auth/";
    private headers: Headers;
    public token: string;
    jwtHelper: JwtHelper = new JwtHelper();

    constructor(private _http: Http) {
        this.headers = new Headers();
        this.headers.append('Content-Type', 'application/json');
        this.headers.append('Accept', 'application/json');
        // set token if saved in local storage
        var currentUser = JSON.parse(localStorage.getItem('currentUser'));
        this.token = currentUser && currentUser.token;
    }
    doLogin(data: any): Observable<boolean> {
        return this._http.post(this.apiUrl, data, { headers: this.headers })
            .map((response: Response) => {

                if (response.status < 200 || response.status >= 300)
                    throw new Error('This request has failed ' + response.status);
                let token = response.json() && response.json().token;
                if (token) {
                    // set token property
                    this.token = token;
                    //console.log(this.jwtHelper.decodeToken(token));
                    let detailsUser = this.jwtHelper.decodeToken(token);
                    let flag:boolean=false;
                    console.log(detailsUser);
                    detailsUser.roles.forEach(element => {
                        if (element.authority === "ROLE_ADMIN") {
                            console.log("Admin");
                            // store username and jwt token in local storage to keep user logged in between page refreshes
                            localStorage.setItem('currentUser', JSON.stringify({ username: data.userName, token: token }));
                            flag=true;
                        }
                    });
                    // return false to indicate successful login but not have permission
                    return flag;
                } else {
                    // return false to indicate failed login
                    return false;
                }
            })

    }

    logout() {
        // remove user from local storage to log user out
        localStorage.removeItem('currentUser');
    }
    loggedIn() {
        return tokenNotExpired(null, localStorage.getItem('currentUser'));
    }


}