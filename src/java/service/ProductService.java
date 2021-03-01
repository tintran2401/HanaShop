/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package service;

import dao.ProductDAO;
import entity.TblProduct;
import entity.TblUser;
import java.time.Instant;
import java.util.Date;
import java.util.List;

/**
 *
 * @author TiTi
 */
public class ProductService {
    
    private ProductDAO productDAO;

    public ProductService() {
        productDAO = new ProductDAO();
    }
    
    public List<TblProduct> getProducts(String productName, float minPrice, float maxPrice) {
        return productDAO.getProducts(productName, minPrice, maxPrice);
    }
    
    public TblProduct getProduct(int id) {
        return productDAO.getProductById(id);
    }
    
    public TblProduct updateProduct(TblProduct product, TblUser user) {
        product.setModifiedBy(user);
        product.setModifiedDate(Date.from(Instant.now()));
        return productDAO.updateProduct(product);
    }
    
    public TblProduct createProduct(TblProduct product) {
        return productDAO.createProduct(product);
    }
}
