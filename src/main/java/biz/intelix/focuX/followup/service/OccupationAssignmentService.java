package biz.intelix.focuX.followup.service;

import biz.intelix.focuX.followup.model.OccupationAssignment;
import biz.intelix.focuX.followup.model.Request;
import biz.intelix.focuX.followup.repository.OccupationAssignmentRepository;
import biz.intelix.focuX.followup.repository.RequestRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.Query;

@Service
public class OccupationAssignmentService {

    private final OccupationAssignmentRepository occupationAssignmentRepository;
    private final RequestRepository requestRepository;
    private final EntityManager entityManager;

    @Autowired
    public OccupationAssignmentService(OccupationAssignmentRepository occupationAssignmentRepository, EntityManager entityManager, RequestRepository requestRepository) {
        this.occupationAssignmentRepository = occupationAssignmentRepository;
        this.entityManager = entityManager;
        this.requestRepository = requestRepository;
    }

    public List<OccupationAssignment> getAllOccupationAssignments() {
        return occupationAssignmentRepository.findAll();
    }

    public ResponseEntity<List<OccupationAssignment>> findOccupationAssignmentsByIdCollaborator(Integer id) {
        List<OccupationAssignment> occupationAssignmentList =
                occupationAssignmentRepository.findOccupationAssignmentsByCollaborator_Id(id);
        return ResponseEntity.ok().body(occupationAssignmentList);
    }

    public ResponseEntity<Set<OccupationAssignment>> findOccupationAssignmentsByIdRequest(Integer id) {
        Set<OccupationAssignment> occupationAssignmentList =
                occupationAssignmentRepository.findOccupationAssignmentsByRequest_Id(id);
        return ResponseEntity.ok().body(occupationAssignmentList);
    }    

    public OccupationAssignment saveOccupationAssignment(OccupationAssignment occupationAssignment) {
        Long totalOccupation = getCollaboratorOccupation(occupationAssignment.getCollaborator().getId());
        if (totalOccupation.intValue() + occupationAssignment.getOccupationPercentage() > 100) {
            throw new IllegalArgumentException("El colaborador con su ocupación actual no puede hacerse cargo de esta ocupación");
        }  else {
            if (occupationAssignment.getRequestRole().getId() == 1) {
                Request request = requestRepository.findById(occupationAssignment.getRequest().getId()).get();                
                request.setResponsibleRequest(occupationAssignment.getCollaborator());
                requestRepository.save(request);
            }
            return occupationAssignmentRepository.save(occupationAssignment);
        }
    }

    public ResponseEntity<OccupationAssignment> updateOccupationAssignment(OccupationAssignment newOccupationAssignment, Integer id) {
        Optional<OccupationAssignment> occupationOpt = occupationAssignmentRepository.findById(id);
        if (occupationOpt.isPresent())  {
            OccupationAssignment occupation = occupationOpt.get();
            Long totalOccupation = getCollaboratorOccupation(occupation.getCollaborator().getId());
            if (totalOccupation + newOccupationAssignment.getOccupationPercentage() - occupation.getOccupationPercentage() > 100) {
                throw new IllegalArgumentException("El colaborador con su ocupación actual no puede hacerse cargo de esta ocupación");
            }  else {
                occupation.setId(newOccupationAssignment.getId());
                occupation.setCode(newOccupationAssignment.getCode());
                occupation.setOccupationPercentage(newOccupationAssignment.getOccupationPercentage());
                occupation.setAssignmentStartDate(newOccupationAssignment.getAssignmentStartDate());
                occupation.setAssignmentEndDate(newOccupationAssignment.getAssignmentEndDate());                    
                occupation.setObservations(newOccupationAssignment.getObservations());                                    
                occupation.setRequestRole(newOccupationAssignment.getRequestRole());
                return ResponseEntity.ok(occupationAssignmentRepository.save(occupation));
            }
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    public OccupationAssignment updateOccupationAssignmentStatus(Integer id, OccupationAssignment occupationAssignment) {

        return occupationAssignmentRepository.findById(id)
                .map(clientWithNewStatus -> {
                    clientWithNewStatus.setIsActive(occupationAssignment.getIsActive());
                    return occupationAssignmentRepository.save(clientWithNewStatus);
                }).get();

    }

    private Long getCollaboratorOccupation(Integer collabId) {
        String query = "SELECT COALESCE(SUM(o.occupationPercentage), 0) from OccupationAssignment as o\n" +
                        "WHERE o.collaborator.id = :id AND o.isActive != 0";
        
        Query q = entityManager.createQuery(query.toString(), Long.class);
        q.setParameter("id", collabId);
        return (Long) (q.getSingleResult() == null ? 0L : q.getSingleResult());
    }

    public List<OccupationAssignment> updateAllCollaboratorAssignments(List<OccupationAssignment> requestAssignments, Integer id) {        
        Long newTotal = 0L;
        for (OccupationAssignment assignment : requestAssignments) {
            newTotal += assignment.getOccupationPercentage();
        }
        if (newTotal > 100) {
            throw new IllegalArgumentException("La ocupación no puede exceder el 100%");
        }
        Comparator<OccupationAssignment> comparator = new Comparator<OccupationAssignment>() {
            public int compare(OccupationAssignment oa1, OccupationAssignment oa2) {
                return oa1.getId().compareTo(oa2.getId());
            }
        };            
        List<OccupationAssignment> colabAssignments = occupationAssignmentRepository.findOccupationAssignmentsByCollaboratorIdAndIsActive(id);
        Collections.sort(requestAssignments, comparator);
        Collections.sort(colabAssignments, comparator);        
        for (int i = 0; i < requestAssignments.size(); i++) {
            OccupationAssignment oldAssignment = colabAssignments.get(i);
            OccupationAssignment newAssignment = requestAssignments.get(i);
            oldAssignment.setAssignmentStartDate(newAssignment.getAssignmentStartDate());
            oldAssignment.setAssignmentEndDate(newAssignment.getAssignmentEndDate());
            oldAssignment.setCode(newAssignment.getCode());
            oldAssignment.setOccupationPercentage(newAssignment.getOccupationPercentage());
            oldAssignment.setObservations(newAssignment.getObservations());            
            occupationAssignmentRepository.save(oldAssignment);
        }
        return colabAssignments;
    }
}
