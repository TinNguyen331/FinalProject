import { Component, OnInit, ElementRef } from '@angular/core';
import { Location } from '@angular/common';
import { Router, ActivatedRoute, NavigationEnd } from '@angular/router';
import { Subscription } from 'rxjs';
import 'rxjs/add/operator/filter';

import { PriceByDayService } from '../../../service/PriceByDayService/pricebyday.service';

declare var $: any;
declare var Chartist: any;
declare var md: any;

@Component({
    selector: 'pricebydaydetails',
    templateUrl: 'pricebydaydetails.component.html',
    providers: [PriceByDayService]
})

export class PriceByDayDetailsComponent implements OnInit {

    public listPrice: any[] = [];
    public subscription: Subscription;
    public id: string;
    public labels: any[] = [];
    public series: any[] = [];
    public high: number;
    public productName: string;


    constructor(private el: ElementRef, private _location: Location,
        private pricebydayService: PriceByDayService, private router: Router,
        private activeRoute: ActivatedRoute) {
        //Code constructor
        router.events.filter(event => event instanceof NavigationEnd).subscribe((val) => {
            this.onChangeUrl();
        });
    }

    async onChangeUrl() {
        this.subscription = this.activeRoute.params.subscribe(params => {
            this.id = params['id'];
        });
        await this.pricebydayService.GetPriceByDayOfProductByProductId(this.id).toPromise().then((data) => {
            console.log(data);
            this.prepareData(data);
        });
        await this.initChart();
    }

    prepareData(data: any) {
        this.high = data[0].price;
        this.productName = data[0].productId.productName;
        data.forEach(element => {
            if (this.high < element.price) {
                this.high = element.price; //Found max price
            }
            this.labels.push(new Date(element.date).toLocaleDateString());
            this.series.push(element.price);
        });
        this.high = Math.ceil(this.high / 10) * 10;//Round to nearest 10
    }

    ngOnInit() {
        $.getScript('../../../assets/js/init/initChartsPage.js');
        //this.initChart();
    }

    initChart() {
        var dataColouredRoundedLineChart = {
            labels: this.labels,
            series: [
                this.series
            ]
        };

        var optionsColouredRoundedLineChart = {
            lineSmooth: Chartist.Interpolation.cardinal({
                tension: 10
            }),
            axisY: {
                showGrid: true,
                offset: 40
            },
            axisX: {
                showGrid: false,
            },
            low: 0,
            high: this.high,
            showPoint: true,
            height: '300px',

        };
        var colouredRoundedLineChart = new Chartist.Line('#colouredRoundedLineChart', dataColouredRoundedLineChart, optionsColouredRoundedLineChart);
        var addedEvents = false;
        colouredRoundedLineChart.on('draw', () => {
            if (!addedEvents) {
                $('.ct-point').on('mouseover', function () {
                    $('#tooltip').html($(this).attr('ct:value')+'$');
                });

                $('.ct-point').on('mouseout', function () {
                    $('#tooltip').html('');
                });
            }
        });
        md.startAnimationForLineChart(colouredRoundedLineChart);
    }
}