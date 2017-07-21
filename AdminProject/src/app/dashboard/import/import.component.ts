import { Component, OnInit } from '@angular/core';
import { PriceByDayService } from '../../service/PriceByDayService/pricebyday.service';
import { ImportModel } from './importModel';

import { ProductService } from '../../service/ProductService/product.service';
import { ImportService } from '../../service/ImportService/import.service';

import 'rxjs/add/operator/toPromise';
import 'rxjs/add/operator/map';

declare var $: any;
declare var swal: any;

@Component({
    moduleId: module.id,
    selector: 'app-import',
    templateUrl: 'import.component.html',
    providers: [ProductService,ImportService]
})

export class ImportComponent implements OnInit {
    listModel: ImportModel[] = [];
    listProduct: any[];
    total:number=0;
    constructor(private productService: ProductService,
    private importService:ImportService) {
    }

    async ngOnInit() {
        await this.loadProduct();
        
        //console.log(this.listProduct);
    }
    async loadProduct() {
        await this.productService.GetAllProduct().toPromise().then((data) => {
            this.listProduct = data;
            $.getScript('../../../assets/js/init/initDataTable.js');
        }).catch((ex) => console.log(ex));
    }
    async loadProductUpdate() {
        await this.productService.GetAllProduct().toPromise().then((data) => {
            this.listProduct = data;
        }).catch((ex) => console.log(ex));
    }
    addProduct(id: string, productName: string) {
        if (!this.checkModelIsExists(id)) {
            let model = new ImportModel();
            model.productId = id;
            model.productName = productName;
            this.listModel.push(model);
            //console.log(model);
        }
        else {
            this.Notify('top', 'center', 'Error: Your product have been added', 'danger');
        }
        this.totalCost();
    }
    checkModelIsExists(id: string) {
        let index: number = this.listModel.findIndex(x => x.productId == id);
        if (index > -1)
            return true;
        return false;
    }
    removeModel(id: string) {
        //console.log(id);
        if (!this.checkModelIsExists(id)) {
            this.Notify('top', 'center', 'Error: Your product have been removed', 'danger');
        }
        else {
            let index = this.listModel.findIndex(x => x.productId == id);
            this.listModel.splice(index, 1);
            //console.log(this.listModel);
        }
        this.totalCost();
    }
    onChangeQuantity(id: string, val: number) {
        let index = this.listModel.findIndex(x => x.productId == id);
        this.listModel[index].quantity = val;
        this.totalCost();
    }
    onChangePrice(id: string, val) {
        let index = this.listModel.findIndex(x => x.productId == id);
        this.listModel[index].originPrice = val;
        this.totalCost();
        //console.log(this.listModel);
    }
    Import(){
        this.importService.AddNewImport(this.listModel).toPromise().then(()=>{
            swal(
                'Success',
                'Your item have been added.',
                'success'
            );
            this.loadProductUpdate();
            this.listModel=[];
        }).catch((ex)=>{
            console.log(ex);
            swal(
                'Error',
                'Some error occurred,pls try again latter !',
                'error'
            )
        })
    }
    Notify(from: any, algin: string, message: string, type: string): void {
        //type = ['', 'info', 'success', 'warning', 'danger', 'rose', 'primary'];
        //color = Math.floor((Math.random() * 6) + 1);
        $.notify({
            icon: "notifications",
            message: message

        }, {
                type: type,
                timer: 30,
                placement: {
                    from: from,
                    align: algin
                }
            });
    }
    totalCost(){
        //this.total=this.listModel.reduce(x=>x.quantity*x.originPrice,0);
        var sum=0;
        this.listModel.forEach(element => {
            sum+=element.quantity*element.originPrice;
        });
        this.total=sum;
    }
    
}