package biz.intelix.focuX.followup.model;

import javax.persistence.*;

@Entity
@Table(name = "CAT_TARGET", schema = "FOLLOWUP")
public class Target {

    @Id
    @Column(name = "id", nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "target_name")
    private String name;

    @Column(name = "target_description")
    private String description;

    @Column(name= "target_type")
    private String type;

    @Column(name = "is_active")
    private Integer isActive;

    public Target() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
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
}

