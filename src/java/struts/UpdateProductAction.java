/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package struts;

import com.opensymphony.xwork2.ActionContext;
import entity.TblCategory;
import entity.TblProduct;
import entity.TblUser;
import java.util.Map;
import service.CategoryService;
import service.ProductService;
import service.UserService;

/**
 *
 * @author TiTi
 */
public class UpdateProductAction {

    private final String SUCCESS = "success";
    private final String FAIL = "fail";

    private String btAction;
    private int productId;
    private String name;
    private String description;
    private String imageUrl;
    private double price;
    private int categoryId;
    private int quantity;
    private String status;

    private String message;

    public UpdateProductAction() {
    }

    public String execute() throws Exception {
        ProductService productService = new ProductService();
        TblProduct product = productService.getProduct(productId);
        
        CategoryService categoryService = new CategoryService();
        TblCategory category = categoryService.getCategory(categoryId);

        String url = SUCCESS;
        try {
            Map session = ActionContext.getContext().getSession();
            UserService userService = new UserService(session);
            TblUser user = userService.getCurrentUser();
            
            if ("Delete".equals(btAction)) {
                product.setStatus("inactive");
                productService.updateProduct(product, user);
            } else if ("Update".equals(btAction)) {
                product.setName(name);
                product.setDescription(description);
                product.setImage(imageUrl);
                product.setPrice(price);
                product.setQuantity(quantity);
                product.setCategoryId(category);
                product.setStatus(status);
                productService.updateProduct(product, user);
            }
        } catch (Exception ex) {
            url = FAIL;
            return url;
        } 
        
        return url;
    }

    /**
     * @return the btAction
     */
    public String getBtAction() {
        return btAction;
    }

    /**
     * @param btAction the btAction to set
     */
    public void setBtAction(String btAction) {
        this.btAction = btAction;
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
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the imageUrl
     */
    public String getImageUrl() {
        return imageUrl;
    }

    /**
     * @param imageUrl the imageUrl to set
     */
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    /**
     * @return the price
     */
    public double getPrice() {
        return price;
    }

    /**
     * @param price the price to set
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * @return the categoryId
     */
    public int getCategoryId() {
        return categoryId;
    }

    /**
     * @param categoryId the categoryId to set
     */
    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    /**
     * @return the quantity
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * @param quantity the quantity to set
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(String status) {
        this.status = status;
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

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

}
