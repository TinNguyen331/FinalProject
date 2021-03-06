import { Injectable } from '@angular/core';
import { Http, Response,Headers ,RequestOptions } from '@angular/http';
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/catch';

@Injectable()
export class ImportService {
    private apiUrl = "http://bomshop.tk:8080/api/import/";

    constructor(private _http: Http) { }
    AddNewImport(data: any): Observable<any> {
        return this._http.post(this.apiUrl, data, this.jwt())
            .map((response: Response) => response.json())
    }
    private jwt() {
        // create authorization header with jwt token
        let currentUser = JSON.parse(localStorage.getItem('currentUser'));
        if (currentUser && currentUser.token) {
            let headers = new Headers({ 'Authorization': currentUser.token });
            headers.append('Content-Type', 'application/json');
            headers.append('Accept','application/json');
            return new RequestOptions({ headers: headers });
        }
    }
}