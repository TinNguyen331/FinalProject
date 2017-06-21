package com.kltn.bo;

/**
 * Created by TinNguyen on 6/21/17.
 */
public class OrderStatisticalDTO {
    private int numberOrderToday=0;
    private double revenueOrderToday=0;
    private double revenueOrderThisMonth=0;

    public int getNumberOrderToday() {
        return numberOrderToday;
    }

    public void setNumberOrderToday(int numberOrderToday) {
        this.numberOrderToday = numberOrderToday;
    }

    public double getRevenueOrderToday() {
        return revenueOrderToday;
    }

    public void setRevenueOrderToday(double revenueOrderToday) {
        this.revenueOrderToday = revenueOrderToday;
    }

    public double getRevenueOrderThisMonth() {
        return revenueOrderThisMonth;
    }

    public void setRevenueOrderThisMonth(double revenueOrderThisMonth) {
        this.revenueOrderThisMonth = revenueOrderThisMonth;
    }
}
