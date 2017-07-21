import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot } from '@angular/router';
import { OrderService } from '../../service/OrderService/order.service';

@Injectable()
export class OrderResolve implements Resolve<any>{

    constructor(private orderService:OrderService){}

    resolve(route:ActivatedRouteSnapshot){
        return this.orderService.GetOrderById(route.params['id']);
    }

}