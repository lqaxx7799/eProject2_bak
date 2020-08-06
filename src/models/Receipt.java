package models;

import java.util.Date;

public class Receipt {

    private int id;
    private Date arrivedTime;
    private Date paidTime;
    private boolean isPaid;
    private int tableId;
    private int accountId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getArrivedTime() {
        return arrivedTime;
    }

    public void setArrivedTime(Date arrivedTime) {
        this.arrivedTime = arrivedTime;
    }

    public Date getPaidTime() {
        return paidTime;
    }

    public void setPaidTime(Date paidTime) {
        this.paidTime = paidTime;
    }

    public boolean isPaid() {
        return isPaid;
    }

    public void setPaid(boolean isPaid) {
        this.isPaid = isPaid;
    }

    public int getTableId() {
        return tableId;
    }

    public void setTableId(int tableId) {
        this.tableId = tableId;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

}
