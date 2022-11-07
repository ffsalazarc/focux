package biz.intelix.focuX.followup.model;

import javax.persistence.*;
import java.util.Calendar;
import java.util.Date;

@Entity
@Table(name = "CAT_VACATION", schema = "FOLLOWUP")
public class Vacation {

    @Id
    @Column(name = "id", nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "id_collaborator")
    private Integer idCollaborator;

    @Column(name = "current_vacation_period")
    private String currentVacationPeriod;

    @Column(name = "current_init_period")
    private Date currentInitPeriod;

    @Column(name = "current_end_period")
    private Date currentEndPeriod;

    @Column(name = "pending_periods")
    private Integer pendingPeriods;

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

    public Vacation() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdCollaborator() {
        return idCollaborator;
    }

    public void setIdCollaborator(Integer idCollaborator) {
        this.idCollaborator = idCollaborator;
    }

    public String getCurrentVacationPeriod() {
        return currentVacationPeriod;
    }

    public void setCurrentVacationPeriod(String currentVacationPeriod) {
        this.currentVacationPeriod = currentVacationPeriod;
    }

    public Date getCurrentInitPeriod() {
        return currentInitPeriod;
    }

    public void setCurrentInitPeriod(Date currentInitPeriod) {
        this.currentInitPeriod = currentInitPeriod;
    }

    public Date getCurrentEndPeriod() {
        return currentEndPeriod;
    }

    public void setCurrentEndPeriod(Date currentEndPeriod) {
        this.currentEndPeriod = currentEndPeriod;
    }

    public Integer getPendingPeriods() {
        return pendingPeriods;
    }

    public void setPendingPeriods(Integer pendingPeriods) {
        this.pendingPeriods = pendingPeriods;
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
