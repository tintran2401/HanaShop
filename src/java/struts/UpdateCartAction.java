/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package struts;

import com.opensymphony.xwork2.ActionContext;
import entity.TblOrder;
import entity.TblOrderDetails;
import java.util.List;
import java.util.Map;
import service.CartService;

/**
 *
 * @author TiTi
 */
public class UpdateCartAction {

    private final String SUCCESS = "success";

    private int quantity;
    private int detailPosition;
    private String btAction;

    public UpdateCartAction() {
    }

    public String execute() throws Exception {
        Map session = ActionContext.getContext().getSession();
        CartService cartService = new CartService(session);
        TblOrder cart = cartService.getCart();

        List<TblOrderDetails> details = (List<TblOrderDetails>) cart.getTblOrderDetailsCollection();

        if ("Update".equals(btAction)) {
            details.get(detailPosition).setQuantity(quantity);
        } else if ("Delete".equals(btAction)) {
            details.remove(detailPosition);
        }
        
        cart.setTblOrderDetailsCollection(details);
        cartService.setCart(cart);

        return SUCCESS;
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
     * @return the detailPosition
     */
    public int getDetailPosition() {
        return detailPosition;
    }

    /**
     * @param detailPosition the detailPosition to set
     */
    public void setDetailPosition(int detailPosition) {
        this.detailPosition = detailPosition;
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

}
