package biz.intelix.focuX.followup.controller;

import biz.intelix.focuX.followup.exception.ResourceNotFoundException;
import biz.intelix.focuX.followup.model.Collaborator;
import biz.intelix.focuX.followup.model.CollaboratorKnowledge;
import biz.intelix.focuX.followup.model.dto.AssigmentDTO;
import biz.intelix.focuX.followup.model.dto.CollaboratorWithOcuppationPercentageDTO;
import biz.intelix.focuX.followup.service.CollaboratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/followup/collaborators")
@CrossOrigin("*")
public class CollaboratorController {

    private CollaboratorService collaboratorService;

    @Autowired
    public CollaboratorController(CollaboratorService collaboratorService) {
        this.collaboratorService = collaboratorService;
    }

    @GetMapping("/all")
    public List<Collaborator> getAllCollaborators() {
        return collaboratorService.getAllCollaborators();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Collaborator> findCollaboratorsById(@PathVariable(value = "id") Integer id)
            throws ResourceNotFoundException {
        return collaboratorService.findCollaboratorsById(id);
    }

    @GetMapping("/technicalskills/{technicalskills}")
    public ResponseEntity<List<Collaborator>> findCollaboratorsByTechnicalSkills(@PathVariable(value = "technicalskills") String technicalSkills)
            throws ResourceNotFoundException {
        return collaboratorService.findCollaboratorsByTechnicalSkills(technicalSkills);
    }

    @PostMapping("/save")
    public Collaborator createCollaborator(@RequestBody Collaborator collaborator) throws ParseException {
        return collaboratorService.saveCollaborator(collaborator);
    }

    @PutMapping("/collaborator/{id}")
    public Collaborator updateCollaborator(@RequestBody Collaborator newCollaborator, @PathVariable(value = "id") Integer id) throws ParseException{
        return collaboratorService.updateCollaborator(newCollaborator, id);
    }

    @PutMapping("/status/{id}")
    public Collaborator inactiveCollaborator(@PathVariable(value = "id") Integer id) throws ParseException{
        return collaboratorService.inactiveCollaborator(id);
    }

    @PutMapping("/knowledge/update/{id}")
    public Collaborator updateCollaboratorKnowledge(@RequestBody List<CollaboratorKnowledge> knowledges, @PathVariable(value = "id") Integer id) throws ParseException  {
        return collaboratorService.updateCollaboratorKnowledge(knowledges, id);
    }

    @GetMapping("/assigments/{id}")
    public AssigmentDTO getOccupationWithProjectAndCollaboratorName(@PathVariable(value = "id") Integer id) {
        return collaboratorService.getOccupationWithProjectAndCollaboratorName(id);
    }

    @GetMapping("/leaders")
    public List<Collaborator> getLeaders() {
        return collaboratorService.getLeaders();
    }

    @GetMapping("/all/occupationpercentage")
    public List<CollaboratorWithOcuppationPercentageDTO> getAllCollaboratorsWithOccupation() {
        return collaboratorService.getAllCollaboratorsWithOccupation();
    }
}
