import { Injectable } from '@angular/core';
import { Http,Response,Headers ,RequestOptions} from '@angular/http';
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/catch';
import 'rxjs/Rx';

@Injectable()
export class OrderService {

    private apiUrl="http://localhost:8080/order/";
    constructor(private _http:Http) { }

    GetAllNewOrder():Observable<any[]>{
        return this._http.get(this.apiUrl+"new",this.jwt())
        .map((response)=>response.json());
        //.catch(this.handleError);
    }
    GetAllSendingOrder():Observable<any[]>{
        return this._http.get(this.apiUrl+"sending",this.jwt())
        .map((response)=>response.json());
        //.catch(this.handleError);
    }
    GetAllCompletedOrder():Observable<any[]>{
        return this._http.get(this.apiUrl+"delivery",this.jwt())
        .map((response)=>response.json());
        //.catch(this.handleError);
    }

    GetOrderById(data:any):Observable<any>{
        return this._http.get(this.apiUrl+data,this.jwt())
        .map((response:Response)=>response.json())
    }

    AddToSending(id:string):Observable<any>{
        return this._http.get(this.apiUrl+"package/"+id,this.jwt())
        .map((response:Response)=>response.json())
    }

    AddToDelivery(id:string):Observable<any>{
        return this._http.get(this.apiUrl+"delivery/"+id,this.jwt())
        .map((response:Response)=>response.json())
    }
    DeleteOrder(id:string):Observable<any>{
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