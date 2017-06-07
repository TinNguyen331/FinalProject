import { Component, OnInit, ElementRef } from '@angular/core';
import { Location } from '@angular/common';
import { CategoryService } from '../../../service/CategoryService/category.service';
import { ProductService } from '../../../service/ProductService/product.service';
import { Router, ActivatedRoute, NavigationEnd } from '@angular/router';
import { Subscription } from 'rxjs';
import 'rxjs/add/operator/filter';

declare var $: any;
declare var swal: any;

@Component({
    moduleId: module.id,
    selector: 'app-updateProduct',
    templateUrl: 'updateProduct.component.html',
    providers: [CategoryService, ProductService]
})

export class UpdateProductComponent implements OnInit {

    public lstCate: any[];
    public pro: any;
    public subscription: Subscription;
    public id: string;

    constructor(private el: ElementRef,
        private _location: Location,
        private productServie: ProductService,
        private categoryService: CategoryService,
        private router: Router,
        private activeRoute: ActivatedRoute) {
        //Code constructor
        router.events.filter(event => event instanceof NavigationEnd).subscribe((val) => {
            this.onChangeUrl();
        });

    }
    //Check when URL have change
    onChangeUrl() {
        this.subscription = this.activeRoute.params.subscribe(params => {
            this.id = params['id'];
        });
        this.loadCate();
        this.productServie.GetProductById(this.id).subscribe((response: any) => {
            this.pro = response;
            setTimeout(() => {
                $('.selectpicker2').val(this.pro.categoryId.id);
                $('.selectpicker2').selectpicker('refresh');
            }, 200);
        });

    }
    UpdateProduct(data: any) {
        console.log(data);
        this.productServie.UpdateProduct(data.id, data).subscribe((response: any) => {
            swal(
                'Success',
                'Your item have been updated.',
                'success'
            );
            this.router.navigate(['/dashboard/product']);
        }, error => {
            swal(
                'Error',
                'Some error occurred,pls try again latter !',
                'error'
            )
        });
    }

    backClick() {
        this._location.back();
    }
    ngOnInit() {
        this.loadCate();
    }
    loadCate() {
        this.categoryService.GetAllCategory().subscribe((response: any) => {
            this.lstCate = response;
        });
    }
}