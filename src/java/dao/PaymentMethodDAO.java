/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.TblPaymentMethod;
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
public class PaymentMethodDAO {

    public List<TblPaymentMethod> getAllPaymentMethods() {
        EntityManager em = DBUtils.getEntityManager();
        try {
            EntityTransaction transaction = em.getTransaction();
            transaction.begin();

            List<TblPaymentMethod> methods = em.createNamedQuery("TblPaymentMethod.findAll")
                    .getResultList();

            transaction.commit();

            return methods;
        } catch (Exception e) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            if (em != null) {
                em.close();
            }
        }

        return null;
    }
    
    public TblPaymentMethod getPaymentMethod(int id) {
        EntityManager em = DBUtils.getEntityManager();
        try {
            EntityTransaction transaction = em.getTransaction();
            transaction.begin();

            List<TblPaymentMethod> methods = em.createNamedQuery("TblPaymentMethod.findById")
                    .setParameter("id", id)
                    .getResultList();

            transaction.commit();

            if (methods != null && !methods.isEmpty()) {
                return methods.get(0);
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
}
