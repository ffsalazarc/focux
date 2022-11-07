package biz.intelix.focuX.followup.model;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "CAT_OCCUPATION_ASSIGNMENT", schema = "FOLLOWUP")
public class OccupationAssignment {

    @Id
    @Column(name = "id", nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_request")
    @JsonBackReference(value ="request-occupation")
    private Request request;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_collaborator")
    @JsonBackReference(value ="collaborator-occupation")
    private Collaborator collaborator;

    @Column(name = "occupation_percentage")
    private Integer occupationPercentage;

    @Column(name = "assignment_start_date")
    private Date assignmentStartDate;

    @Column(name = "assignment_end_date")
    private Date assignmentEndDate;

    @Column(name = "code")
    private String code;

    @Column(name = "observations")
    private String observations;

    @Column(name = "is_active")
    private Integer isActive;

    @ManyToOne(fetch = FetchType.EAGER , optional = false)
    @JoinColumn(name = "id_request_role" , nullable = false)
    private RequestRole requestRole;

    @Transient
    private Integer isInRange;

    public OccupationAssignment() {
    }
    
    @PostLoad()
    private void postLoad() {
        Date today = new Date();
        this.isInRange = today.after(this.assignmentStartDate) && today.before(this.assignmentEndDate) ? 1 : 0;
    }

    public RequestRole getRequestRole() {
        return this.requestRole;
    }

    public void setRequestRole(RequestRole requestRole) {
        this.requestRole = requestRole;
    }
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Request getRequest() {
        return this.request;
    }

    public void setRequest(Request request) {
        this.request = request;
    }

    public Collaborator getCollaborator() {
        return this.collaborator;
    }

    public void setCollaborator(Collaborator collaborator) {
        this.collaborator = collaborator;
    }
   
    public Integer getOccupationPercentage() {
        return occupationPercentage;
    }

    public void setOccupationPercentage(Integer occupationPercentage) {
        this.occupationPercentage = occupationPercentage;
    }

    public Date getAssignmentStartDate() {
        return assignmentStartDate;
    }

    public void setAssignmentStartDate(Date assignmentStartDate) {
        this.assignmentStartDate = assignmentStartDate;
    }

    public Date getAssignmentEndDate() {
        return assignmentEndDate;
    }

    public void setAssignmentEndDate(Date assignmentEndDate) {
        this.assignmentEndDate = assignmentEndDate;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getObservations() {
        return observations;
    }

    public void setObservations(String observations) {
        this.observations = observations;
    }

    public Integer getIsActive() {
        return isActive;
    }

    public void setIsActive(Integer isActive) {
        this.isActive = isActive;
    }


    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof OccupationAssignment)) {
            return false;
        }
        OccupationAssignment occupationAssignment = (OccupationAssignment) o;
        return Objects.equals(id, occupationAssignment.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public Integer getIsInRange() {
        return this.isInRange;
    }

    public void setIsInRange(Integer isInRange) {
        this.isInRange = isInRange;
    }

}
