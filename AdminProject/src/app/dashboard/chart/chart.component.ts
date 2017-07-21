import { Component, OnInit } from '@angular/core';
import initChartsPage = require('../../../assets/js/init/initChartsPage.js');
import { DashboardService } from '../../service/DashboardService/dashboard.service';
import 'rxjs/add/operator/toPromise';
import 'rxjs/add/operator/map';

declare var $: any;
declare var Chartist: any;
declare var md: any;

@Component({
    selector: 'chart',
    templateUrl: 'chart.component.html',
    providers:[DashboardService]
})

export class ChartComponent implements OnInit {
    statistical:any;
    labels:any[]=["Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"];
    revenue:number[]=[];
    cost:any[]=[];
    high:number=100;
    profit:any;

    constructor(private dashboardService:DashboardService) { 
        this.dashboardService.GetRevenue().subscribe((data)=>{
            this.statistical=data;
        });
        
    }
    
    async ngOnInit() {
        $.getScript('../../../assets/js/init/initChartsPage.js');
        await this.prepareData();
        await this.initChart();
     }
     async prepareData(){
        await this.dashboardService.GetProfit().toPromise().then((data)=>{
            this.profit=data;
            this.revenue=data.listRevenue;
            this.cost=data.listCost;
        });
        await this.setMax(this.revenue,this.cost);
     }

     setMax(revenue:number[],cost:number[]){
        let maxRevenue:number=Math.max.apply(null,revenue);
        let maxCost:number=Math.max.apply(null,cost);
        if(maxRevenue<maxCost)
            this.high=maxCost;
        else
            this.high=maxRevenue;

     }

     initChart() {
        var dataColouredRoundedLineChart = {
            labels: this.labels,
            series: [
                this.revenue,this.cost
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
            showArea:true,
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