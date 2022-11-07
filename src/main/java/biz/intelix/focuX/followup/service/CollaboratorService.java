package biz.intelix.focuX.followup.service;

import biz.intelix.focuX.followup.model.Collaborator;
import biz.intelix.focuX.followup.model.CollaboratorKnowledge;
import biz.intelix.focuX.followup.model.Status;
import biz.intelix.focuX.followup.model.dto.AssigmentDTO;
import biz.intelix.focuX.followup.model.dto.CollaboratorWithOcuppationPercentageDTO;
import biz.intelix.focuX.followup.model.dto.OccupationAssigmentDTO;
import biz.intelix.focuX.followup.repository.CollaboratorRepository;
import biz.intelix.focuX.followup.repository.OccupationAssignmentRepository;
import biz.intelix.focuX.followup.repository.StatusRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.Tuple;

@Service
public class CollaboratorService {

    private final CollaboratorRepository collaboratorRepository;
    private final EntityManager entityManager;
    private final StatusRepository statusRepository;
    private final FilterCollaboratorService filterCollaboratorService;
    private final OccupationAssignmentRepository occupationAssignmentRepository;
    private final DtoTransformationsService dtoTransformationsService;
    @Autowired
    public CollaboratorService(CollaboratorRepository collaboratorRepository, EntityManager entityManager, StatusRepository statusRepository, FilterCollaboratorService filterCollaboratorService, OccupationAssignmentRepository occupationAssignmentRepository, DtoTransformationsService dtoTransformationsService) {
        this.collaboratorRepository = collaboratorRepository;
        this.entityManager = entityManager;
        this.statusRepository = statusRepository;
        this.filterCollaboratorService = filterCollaboratorService;
        this.occupationAssignmentRepository = occupationAssignmentRepository;
        this.dtoTransformationsService = dtoTransformationsService;
    }

    public List<Collaborator> getAllCollaborators() {
        return collaboratorRepository.findAll();
    }

    public ResponseEntity<Collaborator> findCollaboratorsById(Integer id) {
        Collaborator collaborator = collaboratorRepository.findCollaboratorById(id);
        return ResponseEntity.ok().body(collaborator);
    }

    public Collaborator saveCollaborator(Collaborator collaborator) {
        if (collaborator.getLeader() != null && collaborator.getLeader().getId().equals(collaborator.getId())) {
            throw new IllegalArgumentException("No se puede asignar a un colaborador su mismo id como lider");
        }
        
        return collaboratorRepository.save(collaborator);
    }

    public Collaborator updateCollaborator(Collaborator newCollaborator, Integer id) {

        if (newCollaborator.getLeader() != null && newCollaborator.getLeader().getId().equals(newCollaborator.getId())) {
            throw new IllegalArgumentException("No se puede asignar a un colaborador su mismo id como lider");
        } 
        
        Optional<Collaborator> oldCollaborator = collaboratorRepository.findById(id);
        if (oldCollaborator.isPresent() && oldCollaborator.get().getIdFile() != 0 && !oldCollaborator.get().getIdFile().equals(newCollaborator.getIdFile())) {
            throw new IllegalArgumentException("No se puede cambiar la ficha de un colaborador una vez es seteada.");
        }    
        
        Optional<Collaborator> collaboratorIdFile = newCollaborator.getIdFile() != 0 ? collaboratorRepository.findCollaboratorByIdFile(newCollaborator.getIdFile()) : Optional.empty();
        if (collaboratorIdFile.isPresent() && ((oldCollaborator.isPresent() && !oldCollaborator.get().getId().equals(collaboratorIdFile.get().getId())) || !oldCollaborator.isPresent())) {
            // Si ya existe un colaborador con esa ficha entonces retornamos error o si no encontramos un colabortador con este id
            throw new IllegalArgumentException("Ya existe la ficha");
        }
        
        if (oldCollaborator.isPresent()) {
            Collaborator collaborator = oldCollaborator.get();
            collaborator.setIdFile(newCollaborator.getIdFile());
            collaborator.setName(newCollaborator.getName());
            collaborator.setLastName(newCollaborator.getLastName());
            collaborator.setEmployeePosition(newCollaborator.getEmployeePosition());
            collaborator.setCompanyEntryDate(newCollaborator.getCompanyEntryDate());
            collaborator.setOrganizationEntryDate(newCollaborator.getOrganizationEntryDate());
            collaborator.setGender(newCollaborator.getGender());
            collaborator.setBornDate(newCollaborator.getBornDate());
            collaborator.setNationality(newCollaborator.getNationality());
            collaborator.setMail(newCollaborator.getMail());
            collaborator.setAssignedLocation(newCollaborator.getAssignedLocation());
            collaborator.setTechnicalSkills(newCollaborator.getTechnicalSkills());
            collaborator.setKnowledges(newCollaborator.getKnowledges() == null ? new ArrayList<>() : newCollaborator.getKnowledges());
            collaborator.setPhones(newCollaborator.getPhones());
            collaborator.setClient(newCollaborator.getClient());
            collaborator.setIsCentralAmerican(newCollaborator.getIsCentralAmerican());
            collaborator.setLeader(newCollaborator.getLeader());
            collaborator.setStatus(newCollaborator.getStatus());
            collaborator.addKnowledges(newCollaborator.getKnowledges());
            collaborator.setImage(newCollaborator.getImage());
            return collaboratorRepository.save(collaborator);      
        } else {
            newCollaborator.setId(id);
            return collaboratorRepository.save(newCollaborator);
        }        
    }

    public Collaborator inactiveCollaborator(Integer id) {
        Status status = statusRepository.findStatusByTypeStatusAndName("Colaborador", "Inactivo");        
        return collaboratorRepository.findById(id)
                .map(collaboratorWithNewStatus -> {
                    collaboratorWithNewStatus.setIsActive(0);
                    collaboratorWithNewStatus.setStatus(status);
                    return collaboratorRepository.save(collaboratorWithNewStatus);
                }).get();

    }

    public ResponseEntity <List<Collaborator>> findCollaboratorsByTechnicalSkills(String technicalSkills) {
        List<Collaborator> collaboratorList = collaboratorRepository.findCollaboratorsByTechnicalSkills(technicalSkills);
        return ResponseEntity.ok().body(collaboratorList);
    }

    public Collaborator updateCollaboratorKnowledge(List<CollaboratorKnowledge> knowledges, Integer id) {
        Collaborator collaborator = collaboratorRepository.getById(id);        
        // We dont get the references were desirializing, so set them up here.
        for (CollaboratorKnowledge knowledge: knowledges) {
            knowledge.setCollaborator(collaborator);
        }
        collaborator.setKnowledges(knowledges);
        return collaboratorRepository.save(collaborator);
    }

    public AssigmentDTO getOccupationWithProjectAndCollaboratorName(Integer colabId) {
        Set<Tuple> tuples = occupationAssignmentRepository.findOccupationAssignmentsWithRequestDetails(colabId);
        Set<OccupationAssigmentDTO> assigments = dtoTransformationsService.getOccupationDTO(tuples);
        String totalPercentageQuery = "SELECT  SUM(occupation.occupationPercentage) \n" +
        "FROM OccupationAssignment as occupation \n" +
        "JOIN Collaborator as c \n" +                       
        "ON c.id = occupation.collaborator.id \n" +
        "WHERE c.id = :colabId";
        
        Query q = entityManager.createQuery(totalPercentageQuery).setParameter("colabId", colabId);
        Long sum = (Long) q.getSingleResult();
        sum = sum == null ? 0L : sum;
        AssigmentDTO assigmentTotal = new AssigmentDTO(sum, assigments, colabId);

        return assigmentTotal;
    }

    public List<Collaborator> getLeaders() {
        String query = "SELECT c.* from \"FOLLOWUP\".\"CAT_COLLABORATOR\" as c \n" +
                        "INNER JOIN \"FOLLOWUP\".\"CAT_EMPLOYEE_POSITION\" as ep \n"+
                        "ON c.id_employee_position = ep.id \n" +
                        "WHERE ep.name SIMILAR TO 'L(í|i)der%' OR ep.name SIMILAR TO 'Gerente%'";

        Query q = entityManager.createNativeQuery(query, Collaborator.class);
        return q.getResultList();        
    }

    public List<CollaboratorWithOcuppationPercentageDTO> getAllCollaboratorsWithOccupation() {
        List<Collaborator> collaborators = this.getAllCollaborators();        
        return filterCollaboratorService.getListofCollaboratorDTO(collaborators);
    }
}