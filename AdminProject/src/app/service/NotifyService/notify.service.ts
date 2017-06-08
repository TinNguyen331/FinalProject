import { Injectable } from '@angular/core';
import { Http,Response,Headers ,RequestOptions} from '@angular/http';
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/catch';
import 'rxjs/Rx';

@Injectable()
export class NotifyService {
    private apiUrl="http://localhost:8080/notify/";
    
    constructor(private _http:Http) {
        
     }
    GetAllNotify():Observable<any[]>{
        return this._http.get(this.apiUrl)
        .map((response)=>response.json());
        //.catch(this.handleError);
    }
    GetAllNotifyLongPolling(){
        return Observable.interval(2000).flatMap(()=>{
            return this._http.get(this.apiUrl+"unread")
            .map((response)=>response.json());
        })
    }
    GetNotifyById(data:any):Observable<any>{
        return this._http.get(this.apiUrl+data,this.jwt())
        .map((response:Response)=>response.json())
    }
    AddNewNotify(data:any):Observable<any>{
       
        return this._http.post(this.apiUrl,data,this.jwt())
        .map((response:Response)=>response.json())
    }
    UpdateNotify(id:string):Observable<any>{
        return this._http.get(this.apiUrl+"status/"+id,this.jwt())
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