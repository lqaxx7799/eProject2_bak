/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

/**
 *
 * @author Admin
 */
public class RevenueStatistic {
    private String time;
    private double revenue;
    private int receiptCount;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public double getRevenue() {
        return revenue;
    }

    public void setRevenue(double revenue) {
        this.revenue = revenue;
    }

    public int getReceiptCount() {
        return receiptCount;
    }

    public void setReceiptCount(int receiptCount) {
        this.receiptCount = receiptCount;
    }
}
