package biz.intelix.focuX.followup.controller;

import biz.intelix.focuX.followup.exception.ResourceNotFoundException;
import biz.intelix.focuX.followup.model.Request;
import biz.intelix.focuX.followup.model.RequestKnowledge;
import biz.intelix.focuX.followup.model.dto.OccupationRoleDTO;
import biz.intelix.focuX.followup.service.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/followup/requests")
@CrossOrigin("*")
public class RequestController {

    private RequestService requestService;

    @Autowired
    public RequestController(RequestService requestService) {
        this.requestService = requestService;
    }

    @GetMapping("/all")
    public List<Request> getAllRequests() {
        return requestService.getAllRequests();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Request> findRequestById(@PathVariable(value = "id") Integer id)
            throws ResourceNotFoundException {
        return requestService.findRequestsById(id);
    }

    @PostMapping("/save")
    public Request createRequest(@RequestBody Request request) throws ParseException {
        return requestService.saveRequest(request);
    }

    @PutMapping("/request/{id}")
    public Request updateRequest(@RequestBody Request newRequest, @PathVariable Integer id) throws ParseException {
        return requestService.updateRequest(newRequest, id);
    }

    @PutMapping("/status/{id}")
    public Request updateRequestStatus(@RequestBody Request request, @PathVariable Integer id) throws ParseException {
        return requestService.updateRequestStatus(id, request);
    }

    @GetMapping("/assigned/{id}")
    public List<OccupationRoleDTO> getCollaboratorsAssigned(@PathVariable Integer id) throws ParseException {
        return requestService.getCollaboratorsAssigned(id);
    }

    @PutMapping("/knowledge/update/{id}")
    public Request updateRequestKnowledge(@RequestBody List<RequestKnowledge> knowledges, @PathVariable(value = "id") Integer id) throws ParseException  {
        return requestService.updateRequestKnowledge(knowledges, id);
    }

    @GetMapping("/client/{clientId}")
    public List<Request> getRequestByClientAndStatus(@PathVariable(value = "clientId") Integer clientId, @RequestParam(required = false) Integer statusId) {
        return requestService.getRequestByClientAndStatus(clientId, statusId);
    }
    
    @GetMapping("/responsible/{responsibleId}")
    public List<Request> getRequestByResponsibleAndStatus(@PathVariable(value = "responsibleId") Integer responsibleId, @RequestParam(required = false) Integer statusId) {
        return requestService.getRequestByResponsibleAndStatus(responsibleId, statusId);
    }

    @GetMapping("/filter/status/{statusId}")
    public List<Request> getRequestsByStatus(@PathVariable(value = "statusId") Integer statusId) {
        return requestService.getRequestByStatus(statusId);
    }
}