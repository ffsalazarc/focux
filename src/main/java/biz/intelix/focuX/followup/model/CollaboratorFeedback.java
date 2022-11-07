package biz.intelix.focuX.followup.model;

import java.util.Date;

import javax.persistence.*;

@Entity
@Table(name = "COLLABORATOR_FEEDBACK", schema = "FOLLOWUP")
public class CollaboratorFeedback {

    @Id
    @Column(name = "id", nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Column(name = "feedback_points")
    private String feedbackPoints;

    @Column(name = "reason_feedback")
    private String reasonFeedback;
    
    @Column(name = "description")
    private String description;
    
    @Column(name = "aggrements")
    private String aggrements;

    @Column(name = "end_date_aggrements")
    private Date endDateAggrements;

    @Column(name = "evaluated_observations")
    private String evaluatedObservations;

    @Column(name = "evaluater_observations")
    private String evaluaterObservations;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "id_responsible", nullable = false)
    private Collaborator responsible;

    @Column(name = "id_performance_evaluations")
    private String idPerformanceEvaluations;
    
    public CollaboratorFeedback () {
    }
    
    public Integer getId() {
        return id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    
    public String getFeedbackPoints() {
        return this.feedbackPoints;
    }

    public void setFeedbackPoints(String feedbackPoints) {
        this.feedbackPoints = feedbackPoints;
    }

    public String getReasonFeedback() {
        return this.reasonFeedback;
    }

    public void setReasonFeedback(String reasonFeedback) {
        this.reasonFeedback = reasonFeedback;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAggrements() {
        return this.aggrements;
    }

    public void setAggrements(String aggrements) {
        this.aggrements = aggrements;
    }

    public Date getEndDateAggrements() {
        return this.endDateAggrements;
    }

    public void setEndDateAggrements(Date endDateAggrements) {
        this.endDateAggrements = endDateAggrements;
    }

    public String getEvaluatedObservations() {
        return this.evaluatedObservations;
    }

    public void setEvaluatedObservations(String evaluatedObservations) {
        this.evaluatedObservations = evaluatedObservations;
    }

    public String getEvaluaterObservations() {
        return this.evaluaterObservations;
    }

    public void setEvaluaterObservations(String evaluaterObservations) {
        this.evaluaterObservations = evaluaterObservations;
    }

    public Collaborator getResponsible() {
        return this.responsible;
    }

    public void setResponsible(Collaborator responsible) {
        this.responsible = responsible;
    }

}