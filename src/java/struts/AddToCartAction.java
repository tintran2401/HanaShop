/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package struts;

import com.opensymphony.xwork2.ActionContext;
import entity.TblOrderDetails;
import entity.TblProduct;
import java.util.Map;
import service.CartService;
import service.ProductService;

/**
 *
 * @author TiTi
 */
public class AddToCartAction {
    
    private final String SUCCESS = "success";
    
    private int productId;
    private String message;
    
    public AddToCartAction() {
    }
    
    public String execute() throws Exception {
        Map session = ActionContext.getContext().getSession();
        CartService cartService = new CartService(session);
        
        ProductService productService = new ProductService();
        TblProduct product = productService.getProduct(productId);
        
        TblOrderDetails item = new TblOrderDetails();
        item.setProductId(product);
        item.setPrice(product.getPrice());
        item.setQuantity(1);
        boolean result = cartService.addToCart(item);
        
        if (!result) {
            message = "Error: Cannot add item " + product.getName() + " to cart!";
        } else {
            message = "Add item " + product.getName() + " success!";
        }
        return SUCCESS;
    }

    /**
     * @return the productId
     */
    public int getProductId() {
        return productId;
    }

    /**
     * @param productId the productId to set
     */
    public void setProductId(int productId) {
        this.productId = productId;
    }

    /**
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * @param message the message to set
     */
    public void setMessage(String message) {
        this.message = message;
    }
    
}
