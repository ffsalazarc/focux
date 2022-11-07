package biz.intelix.focuX.followup.model;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@Entity
@Table(name = "CAT_REQUEST", schema = "FOLLOWUP")
// @JsonIdentityInfo(
//   generator = ObjectIdGenerators.PropertyGenerator.class, 
//   property = "id")

public class Request {

    @Id
    @Column(name = "id", nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "id_client", nullable = false)
    private Client client;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "id_commercial_area", nullable = false)
    private CommercialArea commercialArea;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "id_type_request", nullable = false)
    private TypeRequest typeRequest;

    @Column(name = "title_request")
    private String titleRequest;

    @Column(name = "description_request")
    private String descriptionRequest;

    @ManyToOne(fetch = FetchType.EAGER, optional = true)
    @JoinColumn(name = "id_responsible_request", nullable = true)
    private Collaborator responsibleRequest;

    @Column(name = "priority_order")
    private Integer priorityOrder;

    @Column(name = "date_request")
    private Date dateRequest;

    @Column(name = "date_init")
    private Date dateInit;

    @Column(name = "date_plan_end")
    private Date datePlanEnd;

    @Column(name = "date_real_end")
    private Date dateRealEnd;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "id_status", nullable = false)
    private Status status;

    @Column(name = "completion_percentage")
    private Integer completionPercentage;

    @Column(name = "deviation_percentage")
    private Integer deviationPercentage;

    @Column(name = "deliverables_completed_intelix")
    private String deliverablesCompletedIntelix;

    @Column(name = "pending_activities_intelix")
    private String pendingActivitiesIntelix;

    @Column(name = "comments_intelix")
    private String commentsIntelix;

    @Column(name = "update_date")
    private Date updateDate;

    @Column(name = "comments_client")
    private String commentsClient;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "id_technical_area", nullable = false)
    private TechnicalArea technicalArea;

    @OneToMany(mappedBy = "request")
    @JsonManagedReference(value ="request-occupation")
    Set<OccupationAssignment> collaboratorsAssigned;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "id_category", nullable = false)
    private Category category;

    @Column(name = "internal_feedback_intelix")
    private String internalFeedbackIntelix;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "id_solver_group", nullable = false)
    private Department solverGroup;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "id_request_period", nullable = false)
    private RequestPeriod requestPeriod;

    @Transient
    private Long totalPauseDays;

    @Column(name = "is_active")
    private Integer isActive;

    @Column(name = "code")
    private String code;

    @OneToMany(mappedBy = "request", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<RequestKnowledge> knowledges;

    @OneToMany(mappedBy = "request", cascade = CascadeType.ALL)
    @OrderBy("dateInitPause DESC")
    @JsonManagedReference
    private List<RequestPause> pauses;

    
    @Transient
    private Long totalElapsedDays; // número de días transcurridos desde que inció la solicitud

    @Transient
    private Long deliveryDateDeviation;
    
    public Request() {
    }

    @PostLoad
    private void postLoad() {
        this.totalPauseDays = 0L;
        for (RequestPause pause : pauses) {
            this.totalPauseDays += pause.getTotalPausedDays() != null ? pause.getTotalPausedDays() : 0;
       }
       
        if (this.dateInit != null) {
            if (this.dateRealEnd == null) {
                Date today = new Date();
                this.totalElapsedDays = TimeUnit.DAYS.convert(today.getTime() - dateInit.getTime(), TimeUnit.MILLISECONDS) - totalPauseDays;
                if (today.after(this.datePlanEnd)) {
                    this.deliveryDateDeviation = TimeUnit.DAYS.convert(today.getTime() - datePlanEnd.getTime(), TimeUnit.MILLISECONDS);
                } else {
                    this.deliveryDateDeviation = 0L;
                }
            } else {
                this.totalElapsedDays = TimeUnit.DAYS.convert(dateRealEnd.getTime() - dateInit.getTime(), TimeUnit.MILLISECONDS) - totalPauseDays;
                this.deliveryDateDeviation = 0L;
            }
       }
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public CommercialArea getCommercialArea() {
        return commercialArea;
    }

    public void setCommercialArea(CommercialArea commercialArea) {
        this.commercialArea = commercialArea;
    }

    public TypeRequest getTypeRequest() {
        return typeRequest;
    }

    public void setTypeRequest(TypeRequest typeRequest) {
        this.typeRequest = typeRequest;
    }


    public String getTitleRequest() {
        return titleRequest;
    }

    public void setTitleRequest(String titleRequest) {
        this.titleRequest = titleRequest;
    }

    public String getDescriptionRequest() {
        return descriptionRequest;
    }

    public void setDescriptionRequest(String descriptionRequest) {
        this.descriptionRequest = descriptionRequest;
    }

    public Collaborator getResponsibleRequest() {
        return responsibleRequest;
    }

    public void setResponsibleRequest(Collaborator responsibleRequest) {
        this.responsibleRequest = responsibleRequest;
    }

    public Integer getPriorityOrder() {
        return priorityOrder;
    }

    public void setPriorityOrder(Integer priorityOrder) {
        this.priorityOrder = priorityOrder;
    }

    public Date getDateRequest() {
        return dateRequest;
    }

    public void setDateRequest(Date dateRequest) {
        this.dateRequest = dateRequest;
    }

    public Date getDateInit() {
        return dateInit;
    }

    public void setDateInit(Date dateInit) {
        this.dateInit = dateInit;
    }

    public Date getDatePlanEnd() {
        return datePlanEnd;
    }

    public void setDatePlanEnd(Date datePlanEnd) {
        this.datePlanEnd = datePlanEnd;
    }

    public Date getDateRealEnd() {
        return dateRealEnd;
    }

    public void setDateRealEnd(Date dateRealEnd) {
        this.dateRealEnd = dateRealEnd;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Integer getCompletionPercentage() {
        return completionPercentage;
    }

    public void setCompletionPercentage(Integer completionPercentage) {
        this.completionPercentage = completionPercentage;
    }

    public Integer getDeviationPercentage() {
        return deviationPercentage;
    }

    public void setDeviationPercentage(Integer deviationPercentage) {
        this.deviationPercentage = deviationPercentage;
    }

    public String getDeliverablesCompletedIntelix() {
        return deliverablesCompletedIntelix;
    }

    public void setDeliverablesCompletedIntelix(String deliverablesCompletedIntelix) {
        this.deliverablesCompletedIntelix = deliverablesCompletedIntelix;
    }

    public String getPendingActivitiesIntelix() {
        return pendingActivitiesIntelix;
    }

    public void setPendingActivitiesIntelix(String pendingActivitiesIntelix) {
        this.pendingActivitiesIntelix = pendingActivitiesIntelix;
    }

    public String getCommentsIntelix() {
        return commentsIntelix;
    }

    public void setCommentsIntelix(String commentsIntelix) {
        this.commentsIntelix = commentsIntelix;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public String getCommentsClient() {
        return commentsClient;
    }

    public void setCommentsClient(String commentsClient) {
        this.commentsClient = commentsClient;
    }

    public TechnicalArea getTechnicalArea() {
        return technicalArea;
    }

    public void setTechnicalArea(TechnicalArea technicalArea) {
        this.technicalArea = technicalArea;
    }


    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getInternalFeedbackIntelix() {
        return internalFeedbackIntelix;
    }

    public void setInternalFeedbackIntelix(String internalFeedbackIntelix) {
        this.internalFeedbackIntelix = internalFeedbackIntelix;
    }

    public Department getSolverGroup() {
        return solverGroup;
    }

    public void setSolverGroup(Department solverGroup) {
        this.solverGroup = solverGroup;
    }

    public RequestPeriod getRequestPeriod() {
        return requestPeriod;
    }

    public void setRequestPeriod(RequestPeriod requestPeriod) {
        this.requestPeriod = requestPeriod;
    }


    public Long getTotalPauseDays() {
        return this.totalPauseDays;
    }

    public void setTotalPauseDays(Long totalPauseDays) {
        this.totalPauseDays = totalPauseDays;
    }


    public Integer getIsActive() {
        return isActive;
    }

    public void setIsActive(Integer isActive) {
        this.isActive = isActive;
    }

    public Set<OccupationAssignment> getCollaboratorsAssigned() {
        return this.collaboratorsAssigned;
    }

    public void setCollaboratorsAssigned(Set<OccupationAssignment> collaboratorsAssigned) {
        this.collaboratorsAssigned = collaboratorsAssigned;
    }

    
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }


    public List<RequestKnowledge> getKnowledges() {
        return this.knowledges;
    }

    public void setKnowledges(List<RequestKnowledge> knowledges) {
        this.knowledges = knowledges;
    }   

    public void addKnowledge(RequestKnowledge knowledge) {
        this.knowledges.add(knowledge);
        knowledge.setRequest(this);
    }

    public void removeKnowledge(RequestKnowledge knowledge) {
        this.knowledges.remove(knowledge);
        knowledge.setRequest(null);
        knowledge.setIsActive(0);
    }

    public void addKnowledges(List<RequestKnowledge> knowledges) {
        for (RequestKnowledge knowledge: knowledges) {
            knowledge.setRequest(this);
        }
        this.setKnowledges(knowledges);        
    }

    public void addPause(RequestPause pause) {
        this.pauses.add(pause);
        pause.setRequest(this);
    }

    public void addPauses(List<RequestPause> pauses) {
        for (RequestPause pause: pauses) {
            pause.setRequest(this);
        }
        this.setPauses(pauses);        
    }

    public List<RequestPause> getPauses() {
        return this.pauses;
    }

    public void setPauses(List<RequestPause> pauses) {
        this.pauses = pauses;
    }

    public Long getTotalElapsedDays() {
        return this.totalElapsedDays;
    }

    public void setTotalElapsedDays(Long totalElapsedDays) {
        this.totalElapsedDays = totalElapsedDays;
    }
    
    public Long getDeliveryDateDeviation() {
        return this.deliveryDateDeviation;
    }

    public void setDeliveryDateDeviation(Long deliveryDateDeviation) {
        this.deliveryDateDeviation = deliveryDateDeviation;
    }

}
 