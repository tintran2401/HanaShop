/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package service;

import dao.OrderDAO;
import dao.ProductDAO;
import entity.TblOrder;
import entity.TblOrderDetails;
import entity.TblProduct;
import java.util.ArrayList;
import java.util.Map;
import java.util.Objects;

/**
 *
 * @author TiTi
 */
public class CartService {
    private Map session;

    public CartService(Map session) {
        this.session = session;
    }
    
    public boolean addToCart(TblOrderDetails item) {
        if (!this.ensureCartExist()) {
            return false;
        }
        
        TblOrder cart = this.getCart();

        boolean isFound = false;
        for (TblOrderDetails d : cart.getTblOrderDetailsCollection()) {
            if (Objects.equals(d.getProductId().getId(), item.getProductId().getId())) {
                d.setQuantity(d.getQuantity()+ item.getQuantity());
                isFound = true;
                break;
            }
        }

        if (!isFound) {
            item.setOrderId(cart);
            cart.getTblOrderDetailsCollection().add(item);
        }
        return true;
    }
    
    public boolean ensureCartExist() {
        if (session == null) {
            return false;
        }
        TblOrder cart = this.getCart();
        if (cart == null) {
            cart = new TblOrder();
            cart.setTblOrderDetailsCollection(new ArrayList<>());
            this.setCart(cart);
        }
        return true;
    }
    
    public TblOrder getCart() {
        if (session == null) {
            return null;
        }
        TblOrder cart = (TblOrder) session.get("CART");
        return cart;
    }
    
    public boolean setCart(TblOrder cart) {
        if (session == null) {
            return false;
        }
        session.put("CART", cart);
        return true;
    }
    
    public boolean confirmProduct(TblOrderDetails detail) {
        ProductDAO productDAO = new ProductDAO();
        int productId = detail.getProductId().getId();
        TblProduct product = productDAO.getProductById(productId);
        
        return detail.getQuantity() <= product.getQuantity();
    }
    
    public boolean confirmProducts(TblOrder order) {
        for (TblOrderDetails detail : order.getTblOrderDetailsCollection()) {
            if (!this.confirmProduct(detail)) {
                return false;
            }
        }
        return true;
    }
    
    public boolean saveCart(TblOrder cart) {        
        OrderDAO orderDAO = new OrderDAO();
        TblOrder order = orderDAO.createOrder(cart);
        
        if (order != null) {
            // change products amount
            ProductDAO productDAO = new ProductDAO();
            for (TblOrderDetails detail : cart.getTblOrderDetailsCollection()) {
                TblProduct dbProduct = productDAO.getProductById(detail.getProductId().getId());
                
                int quantity = dbProduct.getQuantity() - detail.getQuantity();
                quantity = quantity < 0 ? 0 : quantity;
                dbProduct.setQuantity(quantity);
                
                productDAO.updateProduct(dbProduct);
            }
            
            return true;
        }
        
        return false;
    }
}
