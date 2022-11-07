package biz.intelix.focuX.followup.model;

import javax.persistence.*;

@Entity
@Table(name = "CAT_EVALUATION", schema = "FOLLOWUP")
public class Evaluation {

    @Id
    @Column(name = "id", nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "minimum_percentage")
    private Integer minimumPercentage;

    @Column(name = "maximum_percentage")
    private Integer maximumPercentage;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "id_target", nullable = false)
    private Target target;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name= "id_indicator", nullable = false)
    private Indicator indicator;

    @Column(name = "name")
    private String name;

    @Column(name = "code")
    private String code;

    @Column(name = "is_active")
    private Integer isActive;

    public Evaluation() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public Integer getMinimumPercentage() {
        return this.minimumPercentage;
    }

    public void setMinimumPercentage(Integer minimumPercentage) {
        this.minimumPercentage = minimumPercentage;
    }

    public Integer getMaximumPercentage() {
        return this.maximumPercentage;
    }

    public void setMaximumPercentage(Integer maximumPercentage) {
        this.maximumPercentage = maximumPercentage;
    }


    public Target getTarget() {
        return target;
    }

    public void setTarget(Target target) {
        this.target = target;
    }

    public Indicator getIndicator() {
        return indicator;
    }

    public void setIndicator(Indicator indicator) {
        this.indicator = indicator;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }


    public Integer getIsActive() {
        return this.isActive;
    }

    public void setIsActive(Integer isActive) {
        this.isActive = isActive;
    }

}
