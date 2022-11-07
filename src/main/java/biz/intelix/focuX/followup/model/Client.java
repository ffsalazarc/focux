package biz.intelix.focuX.followup.model;

import javax.persistence.*;
import java.util.Calendar;

@Entity
@Table(name = "CAT_CLIENT", schema = "FOLLOWUP")
public class Client {

    @Id
    @Column(name = "id", nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "id_business_type", nullable = false)
    private BusinessType businessType;

    @Column(name = "name")
    private String name;

    
    @Column(name = "description")
    private String description;
    
    @Column(name = "is_active")
    private Integer isActive;

    @Column(name = "created")
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar created;

    @Column(name = "createdby")
    private Integer createdby;

    @Column(name = "updated")
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar updated;

    @Column(name = "updatedby")
    private Integer updatedby;


    
    public Client() {
    }
    
    public Integer getId() {
        return id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    
    public BusinessType getBusinessType() {
        return this.businessType;
    }

    public void setBusinessType(BusinessType businessType) {
        this.businessType = businessType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getIsActive() {
        return isActive;
    }

    public void setIsActive(Integer isActive) {
        this.isActive = isActive;
    }

    public Calendar getCreated() { return created; }

    public void setCreated(Calendar created) { this.created = created; }

    public Integer getCreatedBy() {
        return createdby;
    }

    public void setCreatedBy(Integer createdBy) {
        this.createdby = createdBy;
    }

    public Calendar getUpdated() { return updated; }

    public void setUpdated(Calendar updated) { this.updated = updated; }

    public Integer getUpdatedBy() {
        return updatedby;
    }

    public void setUpdatedBy(Integer updatedBy) {
        this.updatedby = updatedBy;
    }

}
