import { Injectable } from '@angular/core';
import { Http,Response,Headers ,RequestOptions} from '@angular/http';
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/catch';
import 'rxjs/add/operator/toPromise';
import 'rxjs/add/operator/map';

@Injectable()
export class SpecialDayService {

    private apiUrl="http://bomshop.tk:8080/api/specialday/";
    constructor(private _http:Http) { }

    GetAllSpeicalDay():Promise<any[]>{
        return this._http.get(this.apiUrl)
        .map((response)=>response.json()).toPromise();
        //.catch(this.handleError);
    }
   GetSpeicalDayById(data:any):Observable<any>{
        return this._http.get(this.apiUrl+data,this.jwt())
        .map((response:Response)=>response.json())
    }
    AddNewSpeicalDay(data:any):Observable<any>{
       
        return this._http.post(this.apiUrl,data,this.jwt())
        .map((response:Response)=> response.json())
    }
    UpdateSpeicalDay(id:string,data:any):Observable<any>{
        return this._http.put(this.apiUrl+id,data,this.jwt())
        .map((response:Response)=>response.json())
    }
    DeleteSpeicalDay(id:any):Observable<any>{
        return this._http.delete(this.apiUrl+id,this.jwt())
        .map((response:Response)=>response.json())
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