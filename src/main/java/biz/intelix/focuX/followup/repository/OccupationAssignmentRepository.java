package biz.intelix.focuX.followup.repository;

import biz.intelix.focuX.followup.model.OccupationAssignment;
import biz.intelix.focuX.followup.model.dto.OccupationAssigmentDTO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

import javax.persistence.Tuple;

@Repository
public interface OccupationAssignmentRepository extends JpaRepository<OccupationAssignment, Integer> {

    List<OccupationAssignment> findOccupationAssignmentsByCollaborator_Id(Integer id);
    Set<OccupationAssignment> findOccupationAssignmentsByRequest_Id(Integer id); 
    
    @Query(value = "SELECT o from OccupationAssignment as o WHERE o.collaborator.id = :id AND o.isActive != 0")
    List<OccupationAssignment> findOccupationAssignmentsByCollaboratorIdAndIsActive(Integer id);  

    @Query(value = "SELECT COALESCE(SUM(o.occupationPercentage), 0) from OccupationAssignment as o WHERE o.collaborator.id = :id AND o.isActive != 0")
    Long occupationAssigmentTotal(Integer id);

    @Query(value = "select \n" +
    "    occupation.\"id\" as id, \n" +
    "    request.\"title_request\" as request, \n" +
    "    request.\"id\" as requestId, \n" +
    "    client.\"name\" as client, \n" +
    "    occupation.\"occupation_percentage\" as occupationPercentage, \n" +
    "    occupation.\"assignment_start_date\" as assignmentStartDate, \n" +
    "    occupation.\"assignment_end_date\" as assignmentEndDate, \n" +
    "    occupation.\"code\" as code, \n" +
    "    occupation.\"observations\" as observations, \n" +
    "    case  \n" +
    "		when CURRENT_DATE >= occupation.\"assignment_start_date\" and CURRENT_DATE <= occupation.\"assignment_end_date\"  \n" +
    "		then 1  \n" +
    "		else 0  \n" +
    "	end as inRange, \n" +
    "	role.\"name\" as role, \n" +
    "    role.\"id\" as roleId, \n" +
    "    businessType.\"name\" as businessType, \n" +
    "    leader.\"name\"|| ' ' ||leader.\"last_name\" as requestLeader, \n" +
    "    request.\"completion_percentage\" as completionPercentage, \n" +
    "    request.\"description_request\" as description,                 \n" +
    "    request.\"date_plan_end\" as datePlanEnd,                 \n" +
    "    request.\"priority_order\" as priority,                 \n" +
    "    occupation.\"is_active\" as isActive \n" +
    "from \"FOLLOWUP\".\"CAT_OCCUPATION_ASSIGNMENT\" occupation      \n" +
    "inner join \"FOLLOWUP\".\"CAT_REQUEST\" request \n" +
    "on occupation.\"id_request\"=request.\"id\" \n" +
    "inner join \"FOLLOWUP\".\"CAT_CLIENT\" client \n" +
    "on client.\"id\"=request.\"id_client\" \n" +
    "inner join \"FOLLOWUP\".\"CAT_BUSINESS_TYPE\" businessType \n" +
    "on  client.\"id_business_type\"=businessType.\"id\"    \n" +
    "inner join \"FOLLOWUP\".\"CAT_REQUEST_ROLE\" role \n" +
    "on occupation.\"id_request_role\"=role.\"id\" \n" +
    "left outer join \"FOLLOWUP\".\"CAT_OCCUPATION_ASSIGNMENT\" occLeader \n" +
    "on occLeader.\"id_request_role\"=1 and occLeader.\"id_request\"=request.\"id\" and occLeader.\"is_active\" = 1 and CURRENT_DATE >= occLeader.\"assignment_start_date\" and CURRENT_DATE <= occLeader.\"assignment_end_date\" \n" +
    "left outer join \"FOLLOWUP\".\"CAT_COLLABORATOR\" leader \n" +
    "on leader.\"id\"=occLeader.\"id_collaborator\" \n" +
    "where occupation.\"id_collaborator\"= :colabId \n", nativeQuery = true)
    Set<Tuple> findOccupationAssignmentsWithRequestDetails(Integer colabId);
}
