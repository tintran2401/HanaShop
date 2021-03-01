/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author TiTi
 */
@Entity
@Table(name = "tbl_Order", catalog = "Lab_HanaShop", schema = "dbo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TblOrder.findAll", query = "SELECT t FROM TblOrder t")
    , @NamedQuery(name = "TblOrder.findById", query = "SELECT t FROM TblOrder t WHERE t.id = :id")
    , @NamedQuery(name = "TblOrder.findByUserAndDateRange", query = "SELECT t FROM TblOrder t WHERE t.username = :user AND t.createdDate >= :fromDate AND t.createdDate <= :toDate")
    , @NamedQuery(name = "TblOrder.findByCreatedDate", query = "SELECT t FROM TblOrder t WHERE t.createdDate = :createdDate")})
public class TblOrder implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "Id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "CreatedDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;
    @JoinColumn(name = "PaymentMethodId", referencedColumnName = "Id")
    @ManyToOne
    private TblPaymentMethod paymentMethodId;
    @JoinColumn(name = "Username", referencedColumnName = "Username")
    @ManyToOne
    private TblUser username;
    @OneToMany(mappedBy = "orderId", cascade = CascadeType.PERSIST)
    private Collection<TblOrderDetails> tblOrderDetailsCollection;

    public TblOrder() {
    }

    public TblOrder(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public TblPaymentMethod getPaymentMethodId() {
        return paymentMethodId;
    }

    public void setPaymentMethodId(TblPaymentMethod paymentMethodId) {
        this.paymentMethodId = paymentMethodId;
    }

    public TblUser getUsername() {
        return username;
    }

    public void setUsername(TblUser username) {
        this.username = username;
    }

    @XmlTransient
    public Collection<TblOrderDetails> getTblOrderDetailsCollection() {
        return tblOrderDetailsCollection;
    }

    public void setTblOrderDetailsCollection(Collection<TblOrderDetails> tblOrderDetailsCollection) {
        this.tblOrderDetailsCollection = tblOrderDetailsCollection;
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
        if (!(object instanceof TblOrder)) {
            return false;
        }
        TblOrder other = (TblOrder) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.TblOrder[ id=" + id + " ]";
    }

}
