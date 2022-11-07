package biz.intelix.focuX.followup.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.Tuple;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import biz.intelix.focuX.followup.model.Collaborator;
import biz.intelix.focuX.followup.model.dto.CollaboratorWithOcuppationPercentageDTO;
import biz.intelix.focuX.followup.model.dto.OccupationAssigmentDTO;
import biz.intelix.focuX.followup.repository.OccupationAssignmentRepository;

@Service
public class FilterCollaboratorService {
    
    private final EntityManager entityManager;
    private final OccupationAssignmentRepository occupationAssignmentRepository;
    private final DtoTransformationsService dtoTransformationsService;
    @Autowired
    FilterCollaboratorService(EntityManager entityManager, OccupationAssignmentRepository occupationAssignmentRepository, DtoTransformationsService dtoTransformationsService) {
        this.entityManager = entityManager.getEntityManagerFactory().createEntityManager();
        this.occupationAssignmentRepository = occupationAssignmentRepository;
        this.dtoTransformationsService = dtoTransformationsService;
    }
    
    public List<CollaboratorWithOcuppationPercentageDTO> getListofCollaboratorDTO(List<Collaborator> collaborators) {        
        List<CollaboratorWithOcuppationPercentageDTO> cDtoList = new ArrayList<>();
        // Código DTO provisional
        for (Collaborator c : collaborators) {
            Long occupationPercentageLong = occupationAssignmentRepository.occupationAssigmentTotal(c.getId());
            Set<Tuple> tuples = occupationAssignmentRepository.findOccupationAssignmentsWithRequestDetails(c.getId());
            Set<OccupationAssigmentDTO> assigments = dtoTransformationsService.getOccupationDTO(tuples);
            Integer occupationPercentage = occupationPercentageLong == null ? 0 : occupationPercentageLong.intValue();
            CollaboratorWithOcuppationPercentageDTO cDto = new CollaboratorWithOcuppationPercentageDTO(c, occupationPercentage, assigments);
            cDtoList.add(cDto);
        }
        return cDtoList;
    }
    
    public List<Collaborator> filterCollaboratorsInChargeOfProjects(Integer clientId) {
        StringBuilder query = new StringBuilder("SELECT DISTINCT c from Collaborator c \n" +
                        "JOIN Request r \n" +
                        "ON r.responsibleRequest.id = c.id \n" +
                        "WHERE r.isActive != 0 AND r.status.name != 'Finalizada' "); // get only requests with status active, ongoing, on pause and not started
        
        if (clientId != null) {
            query.append(" AND r.client.id = :clientId ");
        }
        
        Query q = entityManager.createQuery(query.toString(), Collaborator.class);
        if (clientId != null) q.setParameter("clientId", clientId);

        return q.getResultList();
    }

    public List<CollaboratorWithOcuppationPercentageDTO> filter(List<Integer> knowledgesId, List<Integer> clientsId, List<Integer> employeePositionId, List<Integer> leadersId, List<Integer> departmentsId, List<Integer> colabsId, Date dateInit, Date dateEnd, Long occupation, Integer requestId) {
        StringBuilder query = new StringBuilder("SELECT c from Collaborator c \n");
        StringBuilder where = new StringBuilder("WHERE c.isActive != 0 ");

        String knowledgeQuery = "AND c.id IN \n" +
                                "    (SELECT ck.collaborator.id from CollaboratorKnowledge ck \n" +                                            
                                "    WHERE ck.isActive != 0 AND ck.knowledge.id IN :knowledgesId \n"+
                                "    GROUP BY ck.collaborator.id \n" +
                                "    HAVING COUNT (ck.collaborator.id) = :lenKnowledgesId " +
                                " )\n";
        
        String occupationJoin = "LEFT JOIN c.assigments as ocupation \n" +
                                "ON ocupation.assignmentStartDate >= :dateInit AND ocupation.assignmentEndDate <= :dateEnd \n";
        
        String occupationGroupBy = "GROUP BY c.id \n" +
                                   "HAVING SUM (coalesce(ocupation.occupationPercentage, 0)) < :occupation \n" +
                                   "ORDER BY CONCAT(c.name, c.lastName)";

        String clientQuery = "AND c.client.id IN (:clientsId) ";
        String employeePositionQuery = "AND c.employeePosition.id IN (:employeePositionId)";
        String leadersQuery = "AND c.leader.id IN (:leadersId)";
        String departmentQuery = "AND c.employeePosition.department.id IN (:departmentsId)";
        String requestQuery = "AND c.id NOT IN (SELECT oa.collaborator.id FROM OccupationAssignment oa WHERE oa.isActive = 1 and oa.request.id = :requestId)";
        String colabsQuery = "AND c.id IN (:colabsId)";
        // Primer paso: armar la query    
        if (knowledgesId != null) {
            where.append(knowledgeQuery);
        }
        if (clientsId != null) {
            where.append(clientQuery);
        }
        if (departmentsId != null) {
            where.append(departmentQuery);
        }
        if (leadersId != null) {
            where.append(leadersQuery);
        }
        if (employeePositionId != null) {
            where.append(employeePositionQuery);
        }
        if (requestId != null) {
            where.append(requestQuery);
        }
        if (colabsId != null) {
            where.append(colabsQuery);
        }
        // Este paso debe hacerse al final, porque el occupation debe ir de último
        if (dateInit != null && dateEnd != null) {
            query.append(occupationJoin);
            where.append(occupationGroupBy);
        }
        query.append(where.toString());
        // Segundo paso: sustituir parametros
        Query q = entityManager.createQuery(query.toString(), Collaborator.class);
        if (knowledgesId != null) {
            q.setParameter("knowledgesId", knowledgesId);
            q.setParameter("lenKnowledgesId", Long.valueOf(knowledgesId.size()));
        }
        if (clientsId != null) {
            where.append(clientQuery);
        }
        if (employeePositionId != null) {
            where.append(employeePositionQuery);
        }
        if (leadersId != null) {
            where.append(leadersQuery);
        }
        if (departmentsId != null) {
            where.append(departmentQuery);
        }
        // Este paso debe hacerse al final, porque el occupation debe ir de último
        if (dateInit != null && dateEnd != null) {
            query.append(occupationJoin);
            where.append(occupationGroupBy);
        }
        if (clientsId != null) {
            q.setParameter("clientsId", clientsId);
        }
        if (employeePositionId != null) {
            q.setParameter("employeePositionId", employeePositionId);
        }
        if (leadersId != null) {
            q.setParameter("leadersId", leadersId);
        }
        if (departmentsId != null) {
            q.setParameter("departmentsId", departmentsId);
        }
        if (requestId != null) {
            q.setParameter("requestId", requestId);
        }
        if (colabsId != null) {
            q.setParameter("colabsId", colabsId);
        }
        if (dateInit != null && dateEnd != null) {
            q.setParameter("dateInit", dateInit);
            q.setParameter("dateEnd", dateEnd);
            // Si no se envia la ocupacion, el default es 100
            if (occupation != null) q.setParameter("occupation", occupation);
            else                    q.setParameter("occupation", 100L);
        }

        return getListofCollaboratorDTO(q.getResultList());
    }
}