/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package phu.DTO;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author PC
 */
public class CartDTO {

    private Map<String, BookDTO> cart;

    public CartDTO() {
    }

    public boolean isEmpty() {
        if (cart.size() == 0) {
            return true;
        }
        return false;
    }

    public Map<String, BookDTO> getCart() {

        return cart;
    }

    public void setCart(Map<String, BookDTO> cart) {
        this.cart = cart;
    }

    public void add(BookDTO product) {
        if (this.cart == null) {
            this.cart = new HashMap<>();
        }
        if (this.cart.containsKey(product.getBookID())) {
            int currentQuantity = this.cart.get(product.getBookID()).getQuantity();
            product.setQuantity(currentQuantity + product.getQuantity());
        }
        cart.put(product.getBookID(), product);
    }

    public BookDTO getBook(String id) {
        if (this.cart == null) {
            return null;
        }
        return this.cart.get(id);
    }

    public void delette(String id) {
        if (this.cart == null) {
            return;
        }
        if (this.cart.containsKey(id)) {
            this.cart.remove(id);
        }
    }

    public void update(String id, BookDTO newProduct) {
        if (this.cart == null) {
            return;
        }
        if (this.cart.containsKey(id)) {
            this.cart.replace(id, newProduct);
        }
    }
}
