import { ProductInEventModel } from './productInEvent.model';

export class InsertEventModel{
    eventName: string;
    eventPictureUrl: string;
    fromDate: Date;
    toDate: Date;

    discountProducts: ProductInEventModel[];
}