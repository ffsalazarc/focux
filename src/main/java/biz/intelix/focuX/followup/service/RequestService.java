package biz.intelix.focuX.followup.service;

import biz.intelix.focuX.followup.model.Collaborator;
import biz.intelix.focuX.followup.model.OccupationAssignment;
import biz.intelix.focuX.followup.model.Request;
import biz.intelix.focuX.followup.model.RequestKnowledge;
import biz.intelix.focuX.followup.model.Status;
import biz.intelix.focuX.followup.model.UserEditPlanEndRequest;
import biz.intelix.focuX.followup.model.dto.OccupationRoleDTO;
import biz.intelix.focuX.followup.repository.OccupationAssignmentRepository;
import biz.intelix.focuX.followup.repository.RequestRepository;
import biz.intelix.focuX.followup.repository.StatusRepository;
import biz.intelix.focuX.followup.repository.UserEditPlanEndRequestRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

@Service
public class RequestService {

    private final RequestRepository requestRepository;
    private final EntityManager     entityManager;
    private final UserEditPlanEndRequestRepository userEditPlanEndRequestRepository;
    private final OccupationAssignmentRepository occupationAssignmentRepository;
    private final StatusRepository statusRepository;
    @Autowired
    public RequestService(RequestRepository requestRepository, EntityManager entityManager, UserEditPlanEndRequestRepository userEditPlanEndRequestRepository, OccupationAssignmentRepository occupationAssignmentRepository, StatusRepository statusRepository) {
        this.requestRepository = requestRepository;
        this.entityManager = entityManager;
        this.userEditPlanEndRequestRepository = userEditPlanEndRequestRepository;
        this.occupationAssignmentRepository = occupationAssignmentRepository;
        this.statusRepository = statusRepository;
    }

    public List<Request> getAllRequests() {
        return requestRepository.findAll();
    }

    public ResponseEntity<Request> findRequestsById(Integer id) {
        Request request = requestRepository.findRequestById(id);
        return ResponseEntity.ok().body(request);
    }

    public Request saveRequest(Request request) {
        // Cuando creamos una nueva solicitud su status debe ser Recibida
        if (request.getId() == null) {
            Status status = statusRepository.findStatusByTypeStatusAndName("Solicitud", "Recibida");
            request.setStatus(status);
        }
        return requestRepository.save(request);
    }

    public Request updateRequest(Request newRequest, Integer id) {

        return requestRepository.findById(id)
                .map(request -> {
                    request.setClient(newRequest.getClient());
                    request.setCommercialArea(newRequest.getCommercialArea());
                    request.setTypeRequest(newRequest.getTypeRequest());
                    request.setTitleRequest(newRequest.getTitleRequest());
                    request.setDescriptionRequest(newRequest.getDescriptionRequest());
                    request.setResponsibleRequest(newRequest.getResponsibleRequest());
                    request.setPriorityOrder(newRequest.getPriorityOrder());
                    request.setDateRequest(newRequest.getDateRequest());
                    request.setDateInit(newRequest.getDateInit());
                    if (!newRequest.getDatePlanEnd().equals(request.getDatePlanEnd())) {
                        Date today = new Date(System.currentTimeMillis());
                        UserEditPlanEndRequest userEdit = new UserEditPlanEndRequest(0, request, request.getDatePlanEnd(), newRequest.getDatePlanEnd(), today);
                        userEditPlanEndRequestRepository.save(userEdit);
                    }
                    request.setDatePlanEnd(newRequest.getDatePlanEnd());
                    request.setDateRealEnd(newRequest.getDateRealEnd());
                    request.setStatus(newRequest.getStatus());
                    request.setCompletionPercentage(newRequest.getCompletionPercentage());
                    request.setDeviationPercentage(newRequest.getDeviationPercentage());
                    request.setDeliverablesCompletedIntelix(newRequest.getDeliverablesCompletedIntelix());
                    request.setPendingActivitiesIntelix(newRequest.getPendingActivitiesIntelix());
                    request.setCommentsIntelix(newRequest.getCommentsIntelix());
                    request.setUpdateDate(newRequest.getUpdateDate());
                    request.setCommentsClient(newRequest.getCommentsClient());
                    request.setTechnicalArea(newRequest.getTechnicalArea());
                    request.setCategory(newRequest.getCategory());
                    request.setInternalFeedbackIntelix(newRequest.getInternalFeedbackIntelix());
                    request.setSolverGroup(newRequest.getSolverGroup());
                    request.setRequestPeriod(newRequest.getRequestPeriod());                    
                    request.setIsActive(newRequest.getIsActive());
                    request.setCode(newRequest.getCode());
                    request.addKnowledges(newRequest.getKnowledges());
                    request.addPauses(newRequest.getPauses());
                    return requestRepository.save(request);
                })
                .orElseGet(() -> {
                    newRequest.setId(id);
                    return requestRepository.save(newRequest);
                });
    }

    public Request updateRequestStatus(Integer id, Request request) {

        return requestRepository.findById(id)
                .map(requestWithNewStatus -> {
                    requestWithNewStatus.setIsActive(request.getIsActive());
                    return requestRepository.save(requestWithNewStatus);
                }).get();

    }

    public List<OccupationRoleDTO> getCollaboratorsAssigned(Integer id) {        
        String query = "SELECT new biz.intelix.focuX.followup.model.dto.OccupationRoleDTO(oa.id, oa.occupationPercentage, r.id, c.id, rr.id, oa.observations, oa.code, rr.name, oa.assignmentStartDate, oa.assignmentEndDate, c.name, c.lastName) \n" + 
                        "FROM OccupationAssignment oa \n" +
                        "JOIN Collaborator c\n" +
                        "ON oa.collaborator.id = c.id \n" +
                        "JOIN RequestRole rr\n" +
                        "ON oa.requestRole.id = rr.id \n" +                      
                        "JOIN Request r\n" +
                        "ON oa.request.id = r.id \n" +
                        "WHERE oa.request.id = :requestId and oa.isActive != 0";
    
        Query q = entityManager.createQuery(query);
        q.setParameter("requestId", id);
        List<OccupationRoleDTO> occupationRoles = q.getResultList();
        for (OccupationRoleDTO or : occupationRoles) {
            Long occupationPercentageLong = occupationAssignmentRepository.occupationAssigmentTotal(or.getCollaboratorId());
            Integer occupationPercentage = occupationPercentageLong == null ? 0 : occupationPercentageLong.intValue();
            or.setTotalOccupation(occupationPercentage);
        }
        return occupationRoles;
    }
    
    public List<Request> getRequestByClientAndStatus(Integer clientId, Integer statusId) {
        if (statusId == null) return requestRepository.findRequestByClient_id(clientId);
        else return requestRepository.findRequestByClient_idAndStatus_id(clientId, statusId);
    }

    public List<Request> getRequestByResponsibleAndStatus(Integer responsibleId, Integer statusId) {
        if (statusId == null) return requestRepository.findRequestByResponsibleRequest_id(responsibleId);
        else return requestRepository.findRequestByResponsibleRequest_idAndStatus_id(responsibleId, statusId);
    }

    public List<Request> getRequestByStatus(Integer statusId) {
        return requestRepository.findRequestByStatus_Id(statusId);
    }

    public Request updateRequestKnowledge(List<RequestKnowledge> knowledges, Integer id) {
        
        Request request = requestRepository.getById(id);        
        // We dont get the references were desirializing, so set them up here.
        for (RequestKnowledge knowledge: knowledges) {
            knowledge.setRequest(request);
        }
        request.setKnowledges(knowledges);
        return requestRepository.save(request);
    }
}
