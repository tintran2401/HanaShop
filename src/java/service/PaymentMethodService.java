/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package service;

import dao.PaymentMethodDAO;
import entity.TblPaymentMethod;
import java.util.List;

/**
 *
 * @author TiTi
 */
public class PaymentMethodService {
    
    private PaymentMethodDAO paymentMethodDAO;

    public PaymentMethodService() {
        paymentMethodDAO = new PaymentMethodDAO();
    }
    
    public TblPaymentMethod getPaymentMethod(int id) {
        return paymentMethodDAO.getPaymentMethod(id);
    }
    
    public List<TblPaymentMethod> getAllPaymentMethods() {
        return paymentMethodDAO.getAllPaymentMethods();
    }
}
