package biz.intelix.focuX.followup.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Calendar;
import java.util.Collection;

@Entity
@Table(name = "CAT_USERS", schema = "FOLLOWUP")
public class Users {
    @JsonIgnore
    @Id
    @Column(name = "id", nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

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

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "id_role", nullable = false)
    private Roles role;

    @JsonBackReference
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_collaborator")
    private Collaborator collaborator;

    public Users() {
        //empty constructor
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getIsActive() {
        return isActive;
    }

    public void setIsActive(Integer isActive) {
        this.isActive = isActive;
    }

    public Collaborator getCollaborator() {
        return collaborator;
    }

    public void setCollaborator(Collaborator collaborator) {
        this.collaborator = collaborator;
    }

    public Calendar getCreated() {
        return created;
    }

    public Integer getCreatedby() {
        return createdby;
    }

    public Calendar getUpdated() {
        return updated;
    }

    public Integer getUpdatedby() {
        return updatedby;
    }

    public void setCreated(Calendar created) {
        this.created = created;
    }

    public void setCreatedby(Integer createdby) {
        this.createdby = createdby;
    }

    public void setUpdated(Calendar updated) {
        this.updated = updated;
    }

    public void setUpdatedby(Integer updatedby) {
        this.updatedby = updatedby;
    }

    public Roles getRole() {
        return this.role;
    }

    public void setRole(Roles role) {
        this.role = role;
    }
}
