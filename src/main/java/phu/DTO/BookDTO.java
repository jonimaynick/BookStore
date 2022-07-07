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
public class BookDTO {

    private String bookID;
    private String title;
    private float price;
    private String image;
    private String describe;
    private String author;
    private String category;
    private int quantity;
    private Date importDate;
    private int totalReviews;
    private float rating;
    private boolean status;

    public BookDTO() {
    }

    public Date getImportDate() {
        return importDate;
    }

    public void setImportDate(Date importDate) {
        this.importDate = importDate;
    }

    public BookDTO(String bookID, String title, float price, String image, String describe, String author, String category, int quantity, Date importDate, int totalReviews, float rating, boolean status) {
        this.status = status;
        this.bookID = bookID;
        this.title = title;
        this.price = price;
        this.image = image;
        this.importDate = importDate;
        this.describe = describe;
        this.author = author;
        this.category = category;
        this.quantity = quantity;
        this.totalReviews = totalReviews;
        this.rating = rating;
    }

    public BookDTO(String bookID, String title, float price, String image, String describe, String author, String category, int quantity, int totalReviews, float rating, boolean status) {
        this.status = status;
        this.bookID = bookID;
        this.title = title;
        this.price = price;
        this.image = image;
        this.describe = describe;
        this.author = author;
        this.category = category;
        this.quantity = quantity;
        this.totalReviews = totalReviews;
        this.rating = rating;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getBookID() {
        return bookID;
    }

    public void setBookID(String bookID) {
        this.bookID = bookID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getTotalReviews() {
        return totalReviews;
    }

    public void setTotalReviews(int totalReviews) {
        this.totalReviews = totalReviews;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }
}
