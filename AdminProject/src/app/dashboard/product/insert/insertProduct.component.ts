import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Location } from '@angular/common';
import { CategoryService } from '../../../service/CategoryService/category.service';
import { ProductService } from '../../../service/ProductService/product.service';




declare var $: any;
declare var swal: any;
declare var jQuery: any;

@Component({
    moduleId: module.id,
    selector: 'app-insertProduct',
    templateUrl: 'insertProduct.component.html',
    providers: [CategoryService, ProductService]

})

export class InsertProductComponent implements OnInit {

    public lstCate: any[];
    public defaultImage: string = "../../assets/img/image_placeholder.jpg";
    constructor(private _location: Location,
        private categoryService: CategoryService,
        private productService: ProductService,
        private route: ActivatedRoute,
        private router: Router) {

    }
    backClick() {
        this._location.back();
    }
    ngOnInit() {
        console.log("On Init");
        this.categoryService.GetAllCategory().subscribe((response: any) => {
            this.lstCate =  response;
            setTimeout(()=>{
                $('.selectpicker').selectpicker('refresh');
            },0);
        });
    }
    
    AddProduct(data: any) {
        if (data.categoryId === undefined)
            console.log("undentified");
        else {
            this.productService.AddNewProduct(data).subscribe((response: any) => {
                swal(
                    'Success',
                    'Your item have been added.',
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
    }
}