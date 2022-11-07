package biz.intelix.focuX.followup.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import biz.intelix.focuX.followup.model.Collaborator;
import biz.intelix.focuX.followup.model.dto.CollaboratorWithOcuppationPercentageDTO;
import biz.intelix.focuX.followup.service.*;

@RequestMapping("/api/v1/followup/filtercollaborator")
@RestController
@CrossOrigin("*")
public class FilterCollaboratorController {

    private FilterCollaboratorService filterCollaboratorService;

    @Autowired
    public FilterCollaboratorController(FilterCollaboratorService filterCollaboratorService) {
        this.filterCollaboratorService = filterCollaboratorService;
    }

    @GetMapping("/allby")
    public List<CollaboratorWithOcuppationPercentageDTO> filterCollaboratorsDinamicallyByKnowledgeClientName(@RequestParam(required = false) List<Integer> knowledgesId, @RequestParam(required = false) List<Integer> clientsId, @RequestParam(required = false) List<Integer> employeePositionId, @RequestParam(required = false) List<Integer> leadersId, @RequestParam(required = false) List<Integer> departmentsId, @RequestParam(required = false) List<Integer> colabsId, @RequestParam(required = false) @DateTimeFormat(pattern = "dd-MM-yyyy") Date dateInit, @RequestParam(required = false) @DateTimeFormat(pattern = "dd-MM-yyyy") Date dateEnd, @RequestParam(required = false) Long occupation, @RequestParam(required = false) Integer requestId) {
        return filterCollaboratorService.filter(knowledgesId, clientsId, employeePositionId, leadersId, departmentsId, colabsId, dateInit, dateEnd, occupation, requestId);
    }

    //Collaborators that are in charge of a project in a client
    @GetMapping("/allby/projectleads/")
    public List<Collaborator> filterCollaboratorsInChargeOfProjectsByClient(@RequestParam(required = false) Integer clientId) {
        return filterCollaboratorService.filterCollaboratorsInChargeOfProjects(clientId);
    }
}
