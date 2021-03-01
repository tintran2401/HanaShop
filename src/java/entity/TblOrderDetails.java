/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author TiTi
 */
@Entity
@Table(name = "tbl_OrderDetails", catalog = "Lab_HanaShop", schema = "dbo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TblOrderDetails.findAll", query = "SELECT t FROM TblOrderDetails t")
    , @NamedQuery(name = "TblOrderDetails.findById", query = "SELECT t FROM TblOrderDetails t WHERE t.id = :id")
    , @NamedQuery(name = "TblOrderDetails.findByPrice", query = "SELECT t FROM TblOrderDetails t WHERE t.price = :price")
    , @NamedQuery(name = "TblOrderDetails.findByQuantity", query = "SELECT t FROM TblOrderDetails t WHERE t.quantity = :quantity")})
public class TblOrderDetails implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "Id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "Price", precision = 53)
    private Double price;
    @Column(name = "Quantity")
    private Integer quantity;
    @JoinColumn(name = "OrderId", referencedColumnName = "Id")
    @ManyToOne
    private TblOrder orderId;
    @JoinColumn(name = "ProductId", referencedColumnName = "Id")
    @ManyToOne
    private TblProduct productId;

    public TblOrderDetails() {
    }

    public TblOrderDetails(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public TblOrder getOrderId() {
        return orderId;
    }

    public void setOrderId(TblOrder orderId) {
        this.orderId = orderId;
    }

    public TblProduct getProductId() {
        return productId;
    }

    public void setProductId(TblProduct productId) {
        this.productId = productId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TblOrderDetails)) {
            return false;
        }
        TblOrderDetails other = (TblOrderDetails) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.TblOrderDetails[ id=" + id + " ]";
    }

}
