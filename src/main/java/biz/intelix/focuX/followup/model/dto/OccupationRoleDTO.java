package biz.intelix.focuX.followup.model.dto;

import java.util.Date;

public class OccupationRoleDTO {    
    private Integer occupationId;
    private Integer occupationPercentage;
    private Integer requestId;
    private Integer collaboratorId;
    private Integer roleId;
    private String observations;
    private String code;
    private String roleName;
    private Date startDate;
    private Date endDate;
    private String name;
    private String lastName;
    private Integer totalOccupation;


    public OccupationRoleDTO(Integer occupationId, Integer occupationPercentage, Integer requestId, Integer collaboratorId, Integer roleId, String observations, String code, String roleName, Date startDate, Date endDate, String name, String lastName) {
        this.occupationId = occupationId;
        this.occupationPercentage = occupationPercentage;
        this.requestId = requestId;
        this.collaboratorId = collaboratorId;
        this.roleId = roleId;
        this.observations = observations;
        this.code = code;
        this.roleName = roleName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.name = name;
        this.lastName = lastName;
    }
    
    

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getRoleName() {
        return this.roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public Integer getOccupationPercentage() {
        return this.occupationPercentage;
    }

    public void setOccupationPercentage(Integer occupationPercentage) {
        this.occupationPercentage = occupationPercentage;
    }

    public Integer getCollaboratorId() {
        return this.collaboratorId;
    }

    public void setCollaboratorId(Integer collaboratorId) {
        this.collaboratorId = collaboratorId;
    }

    public Integer getRoleId() {
        return this.roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }


    public Date getStartDate() {
        return this.startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return this.endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Integer getOccupationId() {
        return this.occupationId;
    }

    public void setOccupationId(Integer occupationId) {
        this.occupationId = occupationId;
    }

    public Integer getRequestId() {
        return this.requestId;
    }

    public void setRequestId(Integer requestId) {
        this.requestId = requestId;
    }

    public String getObservations() {
        return this.observations;
    }

    public void setObservations(String observations) {
        this.observations = observations;
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getTotalOccupation() {
        return this.totalOccupation;
    }

    public void setTotalOccupation(Integer totalOccupationM) {
        this.totalOccupation = totalOccupationM;
    }
}