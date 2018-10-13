/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Admin
 */
public class Cart {

    private List<CartItem> cartItems;

    public Cart() {
        cartItems = new ArrayList<>();
    }

    public Cart(List<CartItem> cartItems) {
        this.cartItems = cartItems;
    }
    
    public boolean isExists(CartItem cartItem){
        for (CartItem cartItem_ : cartItems) {
            if(cartItem.getCardId() == cartItem_.getCardId()) return true;
        }
        
        return false;
    }

    // add a new cart item
    public void addCartItem(CartItem cartItem) {
        for (CartItem cartItem_ : cartItems) {
            if(cartItem.getCardId() == cartItem_.getCardId()){
                cartItem_.setQuantity(cartItem_.getQuantity()+ 1);
                return ;
            }
        }
        
        this.cartItems.add(cartItem);
    }
    // remove a cart item

    // update a cart item
    
    //
    public double getTotalAmount(){
        double t = 0;
        for (CartItem cartItem : cartItems) {
            t += cartItem.getPrice() * cartItem.getQuantity();
        }
        
        return t;
    }
    
    public List<CartItem> getCartItems() {
        return cartItems;
    }

    public void setCartItems(List<CartItem> cartItems) {
        this.cartItems = cartItems;
    }

}
