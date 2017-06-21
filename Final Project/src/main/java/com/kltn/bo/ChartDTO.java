package com.kltn.bo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by TinNguyen on 6/21/17.
 */
public class ChartDTO {
    private List<Double> listRevenue;
    private List<Double> listCost;

    public ChartDTO(){
        this.listRevenue=new ArrayList<>();
        this.listCost=new ArrayList<>();
    }

    public List<Double> getListRevenue() {
        return listRevenue;
    }

    public void setListRevenue(List<Double> listRevenue) {
        this.listRevenue = listRevenue;
    }

    public List<Double> getListCost() {
        return listCost;
    }

    public void setListCost(List<Double> listCost) {
        this.listCost = listCost;
    }
}
