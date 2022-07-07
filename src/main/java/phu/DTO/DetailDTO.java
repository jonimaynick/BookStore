/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package phu.DTO;

/**
 *
 * @author PC
 */
public class DetailDTO {

    private String detailID;
    private float price;
    private int quantity;
    private String orderID;
    private String bookID;

    public DetailDTO() {
    }

    public DetailDTO(String detailID, float price, int quantity, String orderID, String bookID) {
        this.detailID = detailID;
        this.price = price;
        this.quantity = quantity;
        this.orderID = orderID;
        this.bookID = bookID;
    }

    public String getDetailID() {
        return detailID;
    }

    public void setDetailID(String detailID) {
        this.detailID = detailID;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public String getBookID() {
        return bookID;
    }

    public void setBookID(String bookID) {
        this.bookID = bookID;
    }
}
