/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.TblProduct;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import utils.DBUtils;

/**
 *
 * @author TiTi
 */
public class ProductDAO {
    
    public List<TblProduct> getProducts(String name, float minPrice, float maxPrice) {
        EntityManager em = DBUtils.getEntityManager();
        try {
            EntityTransaction transaction = em.getTransaction();
            transaction.begin();
            
            List<TblProduct> products = em.createNamedQuery("TblProduct.findByNameAndRangePrice")
                    .setParameter("name", "%" + name + "%")
                    .setParameter("minPrice", minPrice)
                    .setParameter("maxPrice", maxPrice)
                    .getResultList();
            
            transaction.commit();
            
            return products;
        } catch (Exception e) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            if (em != null) {
                em.close();
            }
        }
        
        return null;
    }
    
    public TblProduct getProductById(int id) {
        EntityManager em = DBUtils.getEntityManager();
        try {
            EntityTransaction transaction = em.getTransaction();
            transaction.begin();
            
            List<TblProduct> products = em.createNamedQuery("TblProduct.findById")
                    .setParameter("id", id)
                    .getResultList();
            
            transaction.commit();
            if (products != null && !products.isEmpty()) {
                return products.get(0);
            }
        } catch (Exception e) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            if (em != null) {
                em.close();
            }
        }
        
        return null;
    }
    
    public TblProduct updateProduct(TblProduct product) {
        EntityManager em = DBUtils.getEntityManager();
        try {
            EntityTransaction transaction = em.getTransaction();
            transaction.begin();
            
            em.merge(product);
            
            transaction.commit();
            
            return product;
        } catch (Exception e) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            if (em != null) {
                em.close();
            }
        }
        
        return null;
    }
    
    public TblProduct createProduct(TblProduct product) {
        EntityManager em = DBUtils.getEntityManager();
        try {
            EntityTransaction transaction = em.getTransaction();
            transaction.begin();
            
            em.persist(product);
            
            transaction.commit();
            
            return product;
        } catch (Exception e) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            if (em != null) {
                em.close();
            }
        }
        
        return null;
    }
}
