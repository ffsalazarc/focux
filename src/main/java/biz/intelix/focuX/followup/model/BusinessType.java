package biz.intelix.focuX.followup.model;

import org.jsondoc.core.annotation.ApiObject;
import org.jsondoc.core.annotation.ApiObjectField;

import javax.persistence.*;
import java.util.Calendar;

@Entity
@Table(name = "CAT_BUSINESS_TYPE", schema = "FOLLOWUP")
@ApiObject
public class BusinessType {

    @Id
    @Column(name = "id", nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiObjectField
    private Integer id;

    @ApiObjectField(description = "Representa el codigo del tipo de negocio")
    @Column(name = "code")
    private String code;

    @ApiObjectField(description = "Representa el nombre del tipo de negocio")
    @Column(name = "name")
    private String name;

    @ApiObjectField(description = "Representa la descripción del tipo de negocio")
    @Column(name = "description")
    private String description;

    @ApiObjectField(description = "Campo para inhabilitar un tipo de negocio")
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


    public BusinessType() {
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
