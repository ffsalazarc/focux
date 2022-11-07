package biz.intelix.focuX.followup.model.dto;

import java.util.Set;

public class AssigmentDTO {
    private Long totalOccupation;
    private Set<OccupationAssigmentDTO> assigments;
    private Integer collaboratorId;

    public AssigmentDTO(Long totalOccupation, Set<OccupationAssigmentDTO> assigments, Integer collaboratorId) {
        this.totalOccupation = totalOccupation;
        this.assigments = assigments;
        this.collaboratorId = collaboratorId;
    }

    public AssigmentDTO(Long totalOccupation, Set<OccupationAssigmentDTO> assigments) {
        this.totalOccupation = totalOccupation;
        this.assigments = assigments;
    }

    public Long getTotalOccupation() {
        return this.totalOccupation;
    }

    public void setTotalOccupation(Long totalOccupation) {
        this.totalOccupation = totalOccupation;
    }

    public Set<OccupationAssigmentDTO> getAssigments() {
        return this.assigments;
    }

    public void setAssigments(Set<OccupationAssigmentDTO> assigments) {
        this.assigments = assigments;
    }

    public Integer getCollaboratorId() {
        return this.collaboratorId;
    }

    public void setCollaboratorId(Integer collaboratorId) {
        this.collaboratorId = collaboratorId;
    }
}
