package models;

public class ReceiptDetail {

    private int id;
    private int receiptId;
    private int menuItemId;
    private int quantity;
    private double unitPrice;
    private boolean isMade;
    private boolean isServed;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getReceiptId() {
        return receiptId;
    }

    public void setReceiptId(int receiptId) {
        this.receiptId = receiptId;
    }

    public int getMenuItemId() {
        return menuItemId;
    }

    public void setMenuItemId(int menuItemId) {
        this.menuItemId = menuItemId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public boolean isMade() {
        return isMade;
    }

    public void setMade(boolean isMade) {
        this.isMade = isMade;
    }

    public boolean isServed() {
        return isServed;
    }

    public void setServed(boolean isServed) {
        this.isServed = isServed;
    }

}
