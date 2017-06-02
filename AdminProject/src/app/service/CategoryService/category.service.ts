import { Injectable } from '@angular/core';
import { Http,Response,Headers } from '@angular/http';
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/catch';

@Injectable()
export class CategoryService {
    private apiUrl="http://localhost:8080/categories/";
    private headers: Headers;
    
    constructor(private _http:Http) {
        this.headers=new Headers();
        this.headers.append('Content-Type', 'application/json');
        this.headers.append('Accept','application/json');
     }
    GetAllCategory():Observable<any[]>{
        return this._http.get(this.apiUrl,{headers:this.headers})
        .map((response)=>response.json());
        //.catch(this.handleError);
    }
   GetCategoryById(data:any):Observable<any>{
        return this._http.get(this.apiUrl+data)
        .map((response:Response)=>response.json())
        .catch(this.handleError);
    }
    AddNewCategory(data:any):Observable<any>{
       
        return this._http.post(this.apiUrl,data,{headers: this.headers})
        .map((response:Response)=>response.json())
        .catch(this.handleError);
    }
    UpdateCategory(id:string,data:any):Observable<any>{
        return this._http.put(this.apiUrl+id,data,{headers:this.headers})
        .map((response:Response)=>response.json())
        .catch(this.handleError);
    }
    DeleteCategory(id:any):Observable<any>{
        return this._http.delete(this.apiUrl+id,{headers:this.headers})
        .map((response:Response)=>response.json())
        .catch(this.handleError);
    }

    private handleError(error: any) {
        var applicationError = error.headers.get('Application-Error');
        var serverError = error.json();
        var modelStateErrors: string = '';
 
        if (!serverError.type) {
            console.log(serverError);
            for (var key in serverError) {
                if (serverError[key])
                    modelStateErrors += serverError[key] + '\n';
            }
        }
 
        modelStateErrors = modelStateErrors = '' ? null : modelStateErrors;
 
        return Observable.throw(applicationError || modelStateErrors || 'Server error');
    }

}