package biz.intelix.focuX.followup.model;

import javax.persistence.*;
import java.util.Calendar;

@Entity
@Table(name = "CAT_DEPARTMENT", schema = "FOLLOWUP")
public class Department {

    @Id
    @Column(name = "id", nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "code")
    private String code;

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

    public Department() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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
