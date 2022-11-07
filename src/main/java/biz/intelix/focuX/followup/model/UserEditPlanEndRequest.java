package biz.intelix.focuX.followup.model;

import java.util.Date;

import javax.persistence.*;

@Entity
@Table(name = "USER_EDIT_PLAN_END_REQUEST", schema = "FOLLOWUP")
public class UserEditPlanEndRequest {
    @Id
    @Column(name = "id", nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "id_user")
    Integer userId;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "id_request", nullable = false)
    Request request;

    @Column(name = "past_date")
    Date pastDate;

    @Column(name = "new_date")
    Date newDate;

    @Column(name = "edit_date")
    Date editDate;


    public UserEditPlanEndRequest() {
    }


    public UserEditPlanEndRequest(Integer userId, Request request, Date pastDate, Date newDate, Date editDate) {
        this.userId = userId;
        this.request = request;
        this.pastDate = pastDate;
        this.newDate = newDate;
        this.editDate = editDate;
    }


    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return this.userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Request getRequest() {
        return this.request;
    }

    public void setRequest(Request request) {
        this.request = request;
    }

    public Date getPastDate() {
        return this.pastDate;
    }

    public void setPastDate(Date pastDate) {
        this.pastDate = pastDate;
    }

    public Date getNewDate() {
        return this.newDate;
    }

    public void setNewDate(Date newDate) {
        this.newDate = newDate;
    }

    public Date getEditDate() {
        return this.editDate;
    }

    public void setEditDate(Date editDate) {
        this.editDate = editDate;
    }
}
