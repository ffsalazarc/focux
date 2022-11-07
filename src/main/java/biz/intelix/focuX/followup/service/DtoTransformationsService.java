package biz.intelix.focuX.followup.service;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Tuple;

import org.springframework.stereotype.Service;

import biz.intelix.focuX.followup.model.dto.OccupationAssigmentDTO;

@Service
public class DtoTransformationsService {
    
    public Set<OccupationAssigmentDTO> getOccupationDTO(Set<Tuple> tuples) {
        Set<OccupationAssigmentDTO> assigments = new HashSet<>();
        for (Tuple tuple : tuples) {
            Integer id = (Integer) tuple.get("id");
            String request = (String) tuple.get("request");
            Integer requestId = (Integer) tuple.get("requestId");
            String client = (String) tuple.get("client");
            Integer occupationPercentage = (Integer) tuple.get("occupationPercentage");  
            Date assignmentStartDate = (Date) tuple.get("assignmentStartDate");    
            Date assignmentEndDate = (Date) tuple.get("assignmentEndDate");
            String code = (String) tuple.get("code");
            String observations = (String) tuple.get("observations");    
            String role = (String) tuple.get("role");
            Integer roleId = (Integer) tuple.get("roleId");
            String businessType = (String) tuple.get("businessType");
            String requestLeader = (String) tuple.get("requestLeader");
            Integer completionPercentage = (Integer) tuple.get("completionPercentage");
            Integer isActive = (Integer) tuple.get("isActive");
            String requestDescription = (String) tuple.get("description");
            Date   reqeustPlanEnd = (Date) tuple.get("datePlanEnd");
            Integer requestPriority = (Integer) tuple.get("priority");
            Integer isInRange = (Integer) tuple.get("inRange");
            OccupationAssigmentDTO assignment = new OccupationAssigmentDTO(id, request, requestId, client, occupationPercentage, assignmentStartDate, assignmentEndDate, code, observations, role, roleId, businessType, requestLeader, completionPercentage, requestDescription,reqeustPlanEnd, requestPriority, isInRange,isActive);
            assigments.add(assignment);
        }
        return assigments;
    }
}
