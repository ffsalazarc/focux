package biz.intelix.focuX.followup.model;
import java.util.Date;
import javax.persistence.*;

@Entity
@Table(name = "EVALUATION_PERFORMANCE", schema = "FOLLOWUP")
public class EvaluationPerformance {

    @Id
    @Column(name = "id", nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "id_evaluated", nullable = false)
    private Collaborator evaluated;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "id_evaluator", nullable = false)
    private Collaborator evaluator;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "id_evaluation", nullable = false)
    private Evaluation evaluation;

    @Column(name = "evaluation_date")
    private Date evaluationDate;

    @Column(name = "evaluation_result")
    private Integer evaluationResult;

    @Column(name = "period")
    private Integer period;

    @Column(name = "year")
    private Integer year;

    @Column(name = "weight")
    private Double weight;

    @Column(name = "month")
    private Integer month;

    public EvaluationPerformance () {
    }
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public Collaborator getEvaluated() {
        return this.evaluated;
    }

    public void setEvaluated(Collaborator evaluated) {
        this.evaluated = evaluated;
    }

    public Collaborator getEvaluator() {
        return this.evaluator;
    }

    public void setEvaluator(Collaborator evaluator) {
        this.evaluator = evaluator;
    }

    public Date getEvaluationDate() {
        return this.evaluationDate;
    }

    public void setEvaluationDate(Date evaluationDate) {
        this.evaluationDate = evaluationDate;
    }

    public Integer getEvaluationResult() {
        return this.evaluationResult;
    }

    public void setEvaluationResult(Integer evaluationResult) {
        this.evaluationResult = evaluationResult;
    }

    public Integer getPeriod() {
        return this.period;
    }

    public void setPeriod(Integer period) {
        this.period = period;
    }

    public Evaluation getEvaluation() {
        return this.evaluation;
    }

    public void setEvaluation(Evaluation evaluation) {
        this.evaluation = evaluation;
    }

    public Integer getYear() {
        return this.year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Double getWeight() {
        return this.weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    } 

    public Integer getMonth() {
        return this.month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", evaluated='" + getEvaluated() + "'" +
            ", evaluator='" + getEvaluator() + "'" +
            ", evaluation='" + getEvaluation() + "'" +
            ", evaluationDate='" + getEvaluationDate() + "'" +
            ", evaluationResult='" + getEvaluationResult() + "'" +
            ", period='" + getPeriod() + "'" +
            ", year='" + getYear() + "'" +
            ", weight='" + getWeight() + "'" +
            ", month='" + getMonth() + "'" +
            "}";
    }


}
