package biz.intelix.focuX.followup.model.dto;

import java.util.Date;

public class OccupationAssigmentDTO {
      
    private Integer id;
    private String request;
    private Integer requestId;
    private String client;
    private Integer occupationPercentage;  
    private Date assignmentStartDate;    
    private Date assignmentEndDate;
    private String code;
    private String observations;    
    private String role;
    private Integer roleId;
    private String businessType;
    private String requestLeader;
    private Integer completionPercentage;
    private String requestDescription;
    private Date   requestPlanEnd;
    private Integer requestPriority;
    private Integer isActive;
    private Integer isInRange;


    public OccupationAssigmentDTO(Integer id, String request, Integer requestId, String client, Integer occupationPercentage, Date assignmentStartDate, Date assignmentEndDate, String code, String observations, String role, Integer roleId, String businessType, String requestLeader, Integer completionPercentage, String requestDescription, Date requestPlanEnd, Integer requestPriority, Integer isInRange, Integer isActive) {
        this.id = id;
        this.request = request;
        this.requestId = requestId;
        this.client = client;
        this.occupationPercentage = occupationPercentage;
        this.assignmentStartDate = assignmentStartDate;
        this.assignmentEndDate = assignmentEndDate;
        this.code = code;
        this.observations = observations;
        this.role = role;
        this.roleId = roleId;
        this.businessType = businessType;
        this.requestLeader = requestLeader;
        this.completionPercentage = completionPercentage;
        this.requestDescription = requestDescription;
        this.requestPlanEnd = requestPlanEnd;
        this.requestPriority = requestPriority;
        this.isInRange = isInRange;
        this.isActive = isActive;
    }
    

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRequest() {
        return this.request;
    }

    public void setRequest(String request) {
        this.request = request;
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


    public String getClient() {
        return this.client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public Integer getRequestId() {
        return this.requestId;
    }

    public void setRequestId(Integer requestId) {
        this.requestId = requestId;
    }

    public String getRole() {
        return this.role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Integer getRoleId() {
        return this.roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String getBusinessType() {
        return this.businessType;
    }

    public void setBusinessType(String businessType) {
        this.businessType = businessType;
    }

    public String getRequestLeader() {
        return this.requestLeader;
    }

    public void setRequestLeader(String requestLeader) {
        this.requestLeader = requestLeader;
    }

    public Integer getCompletionPercentage() {
        return this.completionPercentage;
    }

    public void setCompletionPercentage(Integer completionPercentage) {
        this.completionPercentage = completionPercentage;
    }

    public String getRequestDescription() {
        return this.requestDescription;
    }

    public void setRequestDescription(String requestDescription) {
        this.requestDescription = requestDescription;
    }


    public Date getRequestPlanEnd() {
        return this.requestPlanEnd;
    }

    public void setRequestPlanEnd(Date requestPlanEnd) {
        this.requestPlanEnd = requestPlanEnd;
    }

    public Integer getRequestPriority() {
        return this.requestPriority;
    }

    public void setRequestPriority(Integer requestPriority) {
        this.requestPriority = requestPriority;
    }

    public Integer getIsInRange() {
        return this.isInRange;
    }

    public void setIsInRange(Integer isInRange) {
        this.isInRange = isInRange;
    }
}
