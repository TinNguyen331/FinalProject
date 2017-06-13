import { Injectable } from '@angular/core';
import { Http, Response, Headers, RequestOptions } from '@angular/http';
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/catch';

@Injectable()
export class PriceByDayService {
    private apiUrl = "http://localhost:8080/pricebyday/";
    constructor(private _http: Http) { }

    GetAllPriceByDay(): Observable<any[]> {
        return this._http.get(this.apiUrl)
            .map((response) => response.json());
        //.catch(this.handleError);
    }
    GetPriceByDayOfProductByProductId(data: any): Observable<any> {
        return this._http.get(this.apiUrl + data, this.jwt())
            .map((response: Response) => response.json())
    }
    AddNewPriceByDay(data: any): Observable<any> {

        return this._http.post(this.apiUrl, data, this.jwt())
            .map((response: Response) => response.json())
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