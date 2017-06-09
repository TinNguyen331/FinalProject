import { ProductInEventModel } from './productInEvent.model';

export class InsertEventModel{
    id:string;
    eventName: string;
    eventPictureUrl: string;
    fromDate: Date;
    toDate: Date;

    discountProducts: any[];
}