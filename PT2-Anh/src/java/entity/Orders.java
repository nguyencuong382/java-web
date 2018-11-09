/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

/**
 *
 * @author Admin
 */
public class Orders {
    private int orderId;
    private boolean cashOrCard;
    private String currency;
    private String seatNo;
    private float totalAmount;

    public Orders() {
    }

    public Orders(int orderId, boolean cashOrCard, String currency, String seatNo, float totalAmount) {
        this.orderId = orderId;
        this.cashOrCard = cashOrCard;
        this.currency = currency;
        this.seatNo = seatNo;
        this.totalAmount = totalAmount;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public boolean isCashOrCard() {
        return cashOrCard;
    }

    public void setCashOrCard(boolean cashOrCard) {
        this.cashOrCard = cashOrCard;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getSeatNo() {
        return seatNo;
    }

    public void setSeatNo(String seatNo) {
        this.seatNo = seatNo;
    }

    public float getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(float totalAmount) {
        this.totalAmount = totalAmount;
    }
    
    
}
