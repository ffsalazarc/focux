package biz.intelix.focuX.followup.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.Calendar;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "CAT_ROLES", schema = "FOLLOWUP")
public class Roles {

    @Id
    @Column(name = "id", nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

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

    @ManyToMany
    @LazyCollection(LazyCollectionOption.FALSE)
    @JoinTable(
            name = "CAT_ROLES_PRIVILEGES",
            schema = "FOLLOWUP",
            joinColumns = @JoinColumn(
                    name = "id_role", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(
                    name = "id_privilege", referencedColumnName = "id"))
    private Collection<Privilege> privileges;

    public Roles() {
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
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


    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public Collection<Privilege> getPrivileges() {
        return privileges;
    }

    public void setPrivileges(Collection<Privilege> privileges) {
        this.privileges = privileges;
    }

}
