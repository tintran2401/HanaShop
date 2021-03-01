/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package entity;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author TiTi
 */
@Entity
@Table(name = "tbl_PaymentMethod", catalog = "Lab_HanaShop", schema = "dbo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TblPaymentMethod.findAll", query = "SELECT t FROM TblPaymentMethod t")
    , @NamedQuery(name = "TblPaymentMethod.findById", query = "SELECT t FROM TblPaymentMethod t WHERE t.id = :id")
    , @NamedQuery(name = "TblPaymentMethod.findByName", query = "SELECT t FROM TblPaymentMethod t WHERE t.name = :name")})
public class TblPaymentMethod implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "Id", nullable = false)
    private Integer id;
    @Column(name = "Name", length = 500)
    private String name;
    @OneToMany(mappedBy = "paymentMethodId")
    private Collection<TblOrder> tblOrderCollection;

    public TblPaymentMethod() {
    }

    public TblPaymentMethod(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @XmlTransient
    public Collection<TblOrder> getTblOrderCollection() {
        return tblOrderCollection;
    }

    public void setTblOrderCollection(Collection<TblOrder> tblOrderCollection) {
        this.tblOrderCollection = tblOrderCollection;
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
        if (!(object instanceof TblPaymentMethod)) {
            return false;
        }
        TblPaymentMethod other = (TblPaymentMethod) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.TblPaymentMethod[ id=" + id + " ]";
    }

}
