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
@Table(name = "tbl_Product", catalog = "Lab_HanaShop", schema = "dbo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TblProduct.findAll", query = "SELECT t FROM TblProduct t")
    , @NamedQuery(name = "TblProduct.findById", query = "SELECT t FROM TblProduct t WHERE t.id = :id")
    , @NamedQuery(name = "TblProduct.findByName", query = "SELECT t FROM TblProduct t WHERE t.name = :name")
    , @NamedQuery(name = "TblProduct.findByNameAndRangePrice", query = "SELECT t FROM TblProduct t WHERE t.name LIKE :name AND t.price >= :minPrice AND t.price <= :maxPrice AND t.status = 'active' ORDER BY t.createdDate DESC")
    , @NamedQuery(name = "TblProduct.findByImage", query = "SELECT t FROM TblProduct t WHERE t.image = :image")
    , @NamedQuery(name = "TblProduct.findByDescription", query = "SELECT t FROM TblProduct t WHERE t.description = :description")
    , @NamedQuery(name = "TblProduct.findByPrice", query = "SELECT t FROM TblProduct t WHERE t.price = :price")
    , @NamedQuery(name = "TblProduct.findByQuantity", query = "SELECT t FROM TblProduct t WHERE t.quantity = :quantity")
    , @NamedQuery(name = "TblProduct.findByCreatedDate", query = "SELECT t FROM TblProduct t WHERE t.createdDate = :createdDate")
    , @NamedQuery(name = "TblProduct.findByModifiedDate", query = "SELECT t FROM TblProduct t WHERE t.modifiedDate = :modifiedDate")
    , @NamedQuery(name = "TblProduct.findByStatus", query = "SELECT t FROM TblProduct t WHERE t.status = :status")})
public class TblProduct implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "Id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "Name", length = 500)
    private String name;
    @Column(name = "Image", length = 500)
    private String image;
    @Column(name = "Description", length = 2147483647)
    private String description;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "Price", precision = 53)
    private Double price;
    @Column(name = "Quantity")
    private Integer quantity;
    @Column(name = "CreatedDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;
    @Column(name = "ModifiedDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date modifiedDate;
    @Column(name = "Status", length = 50)
    private String status;
    @OneToMany(mappedBy = "productId")
    private Collection<TblOrderDetails> tblOrderDetailsCollection;
    @JoinColumn(name = "CategoryId", referencedColumnName = "Id")
    @ManyToOne
    private TblCategory categoryId;
    @JoinColumn(name = "ModifiedBy", referencedColumnName = "Username")
    @ManyToOne
    private TblUser modifiedBy;

    public TblProduct() {
    }

    public TblProduct(Integer id) {
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @XmlTransient
    public Collection<TblOrderDetails> getTblOrderDetailsCollection() {
        return tblOrderDetailsCollection;
    }

    public void setTblOrderDetailsCollection(Collection<TblOrderDetails> tblOrderDetailsCollection) {
        this.tblOrderDetailsCollection = tblOrderDetailsCollection;
    }

    public TblCategory getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(TblCategory categoryId) {
        this.categoryId = categoryId;
    }

    public TblUser getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(TblUser modifiedBy) {
        this.modifiedBy = modifiedBy;
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
        if (!(object instanceof TblProduct)) {
            return false;
        }
        TblProduct other = (TblProduct) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.TblProduct[ id=" + id + " ]";
    }

}
