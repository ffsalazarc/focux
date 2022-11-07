package biz.intelix.focuX.followup.model;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "CAT_PRIVILEGE", schema = "FOLLOWUP")
public class Privilege {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name")
    private String name;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
