/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package phu.DTO;

import java.util.Date;

/**
 *
 * @author PC
 */
public class OrderDTO {

    private String orderID;
    private Date orderDate;
    private float total;
    private String userID;
    private boolean status;
    private String CouponsCode;

    public OrderDTO() {
    }

    public OrderDTO(String orderID, Date orderDate, float total, String userID, boolean status, String CouponsCode) {
        this.orderID = orderID;
        this.orderDate = orderDate;
        this.total = total;
        this.userID = userID;
        this.status = status;
        this.CouponsCode = CouponsCode;
    }

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getCouponsCode() {
        return CouponsCode;
    }

    public void setCouponsCode(String CouponsCode) {
        this.CouponsCode = CouponsCode;
    }
}
