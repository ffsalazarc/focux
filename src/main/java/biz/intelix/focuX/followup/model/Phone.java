package biz.intelix.focuX.followup.model;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;

import java.util.Calendar;

@Entity
@Table(name = "CAT_PHONE", schema = "FOLLOWUP")
public class Phone {
    @Id
    @Column(name = "id", nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_collaborator", nullable = false)
    @JsonBackReference
    private Collaborator collaborator;

    @Column(name = "number")
    private String number;

    @Column(name = "type")
    private String type;

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
    
    public Phone() {
    }

    public Phone(Integer id, Collaborator collaborator, String number) {
        this.id = id;
        this.collaborator = collaborator;
        this.number = number;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Collaborator getCollaborator() {
        return this.collaborator;
    }

    public void setCollaborator(Collaborator collaborator) {
        this.collaborator = collaborator;
    }

    public String getNumber() {
        return this.number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Phone id(Integer id) {
        setId(id);
        return this;
    }

    public Phone collaborator(Collaborator collaborator) {
        setCollaborator(collaborator);
        return this;
    }

    public Phone number(String number) {
        setNumber(number);
        return this;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getIsActive() {
        return this.isActive;
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
