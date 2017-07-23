import { Injectable } from '@angular/core';
import { Http, Response, Headers, RequestOptions } from '@angular/http';
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/catch';

@Injectable()
export class DashboardService {

    private apiUrl = "http://bomshop.tk:8080/api/order/";
    constructor(private _http: Http) { }

    GetRevenue(): Observable<any> {
        return this._http.get(this.apiUrl + "revenue", this.jwt())
            .map((response) => response.json());
    }
    GetProfit():Observable<any>{
        return this._http.get(this.apiUrl + "profit", this.jwt())
            .map((response) => response.json());
    }
    private jwt() {
        // create authorization header with jwt token
        let currentUser = JSON.parse(localStorage.getItem('currentUser'));
        if (currentUser && currentUser.token) {
            let headers = new Headers({ 'Authorization': currentUser.token });
            headers.append('Content-Type', 'application/json');
            headers.append('Accept', 'application/json');
            return new RequestOptions({ headers: headers });
        }
    }
}