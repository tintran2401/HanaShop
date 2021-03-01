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
import java.time.Instant;
import java.util.Date;
import java.util.Map;
import service.CategoryService;
import service.ProductService;
import service.UserService;

/**
 *
 * @author TiTi
 */
public class CreateProductAction {
    
    private final String SUCCESS = "success";
    
    private String name;
    private String image;
    private String description;
    private double price;
    private int quantity;
    private int categoryId;
    
    private String message;
    
    public CreateProductAction() {
    }
    
    public String execute() throws Exception {
        Map session = ActionContext.getContext().getSession();
        UserService userService = new UserService(session);
        TblUser user = userService.getCurrentUser();
        
        CategoryService categoryService = new CategoryService();
        TblCategory category = categoryService.getCategory(categoryId);
        
        TblProduct product = new TblProduct();
        
        product.setName(name);
        product.setImage(image);
        product.setDescription(description);
        product.setPrice(price);
        product.setQuantity(quantity);
        product.setCategoryId(category);
        product.setStatus("active");
        product.setCreatedDate(Date.from(Instant.now()));
        product.setModifiedDate(Date.from(Instant.now()));
        product.setModifiedBy(user);
        
        ProductService productService = new ProductService();
        productService.createProduct(product);
        
        message = "Create new product success!";
        return SUCCESS;
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
     * @return the image
     */
    public String getImage() {
        return image;
    }

    /**
     * @param image the image to set
     */
    public void setImage(String image) {
        this.image = image;
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
