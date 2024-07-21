/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.sp24.t4s4.shopping;

/**
 *
 * @author admin
 */
public class ProductError {
    private String id;
    private String errorMessage;
    private String name;

    public ProductError() {
    }

    public ProductError(String id, String errorMessage, String name) {
        this.id = id;
        this.errorMessage = errorMessage;
        this.name = name;
    }
    
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
}
