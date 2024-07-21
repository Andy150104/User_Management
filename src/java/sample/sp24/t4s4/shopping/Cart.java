/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.sp24.t4s4.shopping;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author admin
 */
public class Cart {
    private Map<String, Computer>cart;

    public Cart() {
    }

    public Cart(Map<String, Computer> cart) {
        this.cart = cart;
    }

    public Map<String, Computer> getCart() {
        return cart;
    }

    public void setCart(Map<String, Computer> cart) {
        this.cart = cart;
    }

    public boolean add(Computer computer) {
        boolean check=false;
        try {
            if(this.cart==null){
                this.cart=new HashMap<>();
            }
            if(this.cart.containsKey(computer.getId())){
                int currenQuantity=this.cart.get(computer.getId()).getQuantity();
                computer.setQuantity(currenQuantity+computer.getQuantity());
            }
            cart.put(computer.getId(), computer);
            check=true;
        } catch (Exception e) {
        }
        return check;
    }

    public boolean edit(String id, int quantity) {
        boolean check=false;
        try {
            if(this.cart!=null){
                if(this.cart.containsKey(id)){
                    Computer computer=this.cart.get(id);
                    computer.setQuantity(quantity);
                    this.cart.replace(id, computer);
                    check=true;
                }
            }
        } catch (Exception e) {
        }
        return check;
    }

    public boolean remove(String id) {
        boolean check=false;
        try {
            if(this.cart!=null){
                if(this.cart.containsKey(id)){
                    Computer computer=this.cart.get(id);
                    this.cart.remove(id);
                    check=true;
                }
            }
        } catch (Exception e) {
        }
        return check;
    }

    
}
