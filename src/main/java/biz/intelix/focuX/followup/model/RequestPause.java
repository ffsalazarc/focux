package biz.intelix.focuX.followup.model;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PostLoad;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Transient;
import com.fasterxml.jackson.annotation.JsonBackReference;
@Entity
@Table(name = "CAT_REQUEST_PAUSES", schema = "FOLLOWUP")
public class RequestPause {
    @Id
    @Column(name = "id", nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "id_request", nullable = false)
    @JsonBackReference
    private Request request;

    @Column(name = "date_init")
    private Date dateInitPause;

    @Column(name = "date_end")
    private Date dateEndPause;

    @Column(name = "comments")
    private String comments;

    @Column(name = "created_by")
    private Integer createdBy;

    @Transient
    private Long totalPausedDays;

    @Column(name = "created_on", updatable = false)
    private Date createdOn;

    @PostLoad
    private void postLoad() {
        if (this.dateEndPause == null) {
            Date today = new Date();
            this.totalPausedDays = TimeUnit.DAYS.convert(today.getTime() - dateInitPause.getTime(), TimeUnit.MILLISECONDS);
        } else {
            this.totalPausedDays = TimeUnit.DAYS.convert(dateEndPause.getTime() - dateInitPause.getTime(), TimeUnit.MILLISECONDS);
        }
    }

    @PrePersist
    protected void onCreate() {
        createdOn = new Date();
    }

    public RequestPause() {}


    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Request getRequest() {
        return this.request;
    }

    public void setRequest(Request request) {
        this.request = request;
    }

    public Date getDateInitPause() {
        return this.dateInitPause;
    }

    public void setDateInitPause(Date dateInitPause) {
        this.dateInitPause = dateInitPause;
    }

    public Date getDateEndPause() {
        return this.dateEndPause;
    }

    public void setDateEndPause(Date dateEndPause) {
        this.dateEndPause = dateEndPause;
    }

    public Long getTotalPausedDays() {
        return this.totalPausedDays;
    }

    public void setTotalPausedDays(Long totalPausedDays) {
        this.totalPausedDays = totalPausedDays;
    }

    public String getComments() {
        return this.comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public Integer getCreatedBy() {
        return this.createdBy;
    }

    public void setCreatedBy(Integer createdBy) {
        this.createdBy = createdBy;
    }

    public Date getCreatedOn() {
        return this.createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }
}
