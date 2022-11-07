package biz.intelix.focuX.followup.model;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "EVALUATION_TEMPLATE", schema = "FOLLOWUP")
public class EvaluationTemplate {

    @Id
    @Column(name = "id", nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "id_template_name", nullable = false)
    @JsonBackReference
    private EvaluationTemplateName evaluationTemplateName;

    // TODO: Aún no estoy seguro de la cardinalidad de esta relación, puede ser uno a muchos o uno a uno
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "id_evaluation", nullable = false)
    private Evaluation evaluation;

    public EvaluationTemplate () {
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof EvaluationTemplate)) return false;
        return id != null && id.equals(((EvaluationTemplate) o).getId());
    }

    // hashcode necesita ser un valor constante
    // ref: https://vladmihalcea.com/the-best-way-to-map-a-onetomany-association-with-jpa-and-hibernate/
    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public EvaluationTemplateName getEvaluationTemplateName() {
        return this.evaluationTemplateName;
    }

    public void setEvaluationTemplateName(EvaluationTemplateName evaluationTemplateName) {
        this.evaluationTemplateName = evaluationTemplateName;
    }
   

    public Evaluation getEvaluation() {
        return this.evaluation;
    }

    public void setEvaluation(Evaluation evaluation) {
        this.evaluation = evaluation;
    }
    

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +           
            ", evaluation='" + getEvaluation() + "'" +
            "}";
    }
    
}
