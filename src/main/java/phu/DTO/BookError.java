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
public class BookError {

    private String bookID;
    private String error;

    public BookError(String bookID, String error) {
        this.bookID = bookID;
        this.error = error;
    }

    public String getBookID() {
        return bookID;
    }

    public void setBookID(String bookID) {
        this.bookID = bookID;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
