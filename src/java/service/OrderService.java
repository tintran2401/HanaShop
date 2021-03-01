/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import dao.OrderDAO;
import entity.TblOrder;
import entity.TblOrderDetails;
import entity.TblUser;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author TiTi
 */
public class OrderService {

    private OrderDAO orderDAO;

    public OrderService() {
        orderDAO = new OrderDAO();
    }

    public List<TblOrder> getOrders(TblUser user, String productName, Date fromDate, Date toDate) {
        List<TblOrder> orders = orderDAO.getOrders(user, fromDate, toDate);

        List<TblOrder> ordersToRemove = new ArrayList<>();

        productName = productName.toLowerCase();
        for (TblOrder order : orders) {
            List<TblOrderDetails> detailsToRemove = new ArrayList<>();
            List<TblOrderDetails> details = (List<TblOrderDetails>) order.getTblOrderDetailsCollection();
            
            for (TblOrderDetails detail : details) {
                if (!detail.getProductId().getName().toLowerCase().contains(productName)) {
                    detailsToRemove.add(detail);
                }
            }
            
            details.removeAll(detailsToRemove);
            order.setTblOrderDetailsCollection(details);
            if (details.isEmpty()) {
                ordersToRemove.add(order);
            }
        }
        
        orders.removeAll(ordersToRemove);

        return orders;
    }
}
