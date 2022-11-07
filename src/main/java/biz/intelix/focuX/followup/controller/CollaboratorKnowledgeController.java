package biz.intelix.focuX.followup.controller;

import biz.intelix.focuX.followup.exception.ResourceNotFoundException;
import biz.intelix.focuX.followup.model.CollaboratorKnowledge;
import biz.intelix.focuX.followup.service.CollaboratorKnowledgeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/followup/collaboratorknowledge")
@CrossOrigin("*")
public class CollaboratorKnowledgeController {

    private CollaboratorKnowledgeService collaboratorKnowledgeService;

    @Autowired
    public CollaboratorKnowledgeController(CollaboratorKnowledgeService collaboratorKnowledgeService) {
        this.collaboratorKnowledgeService = collaboratorKnowledgeService;
    }

    @GetMapping("/all")
    public List<CollaboratorKnowledge> getAllCollaboratorKnowledge() {
        return collaboratorKnowledgeService.getAllCollaboratorKnowledges();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CollaboratorKnowledge> findCollaboratorKnowledgeByIdFile(@PathVariable(value = "id") Integer id)
            throws ResourceNotFoundException {
        return collaboratorKnowledgeService.findCollaboratorKnowledgeById(id);
    }

    @PostMapping("/save")
    public CollaboratorKnowledge createCollaboratorKnowledge(@RequestBody CollaboratorKnowledge businessType)
            throws ParseException {
        return collaboratorKnowledgeService.saveCollaboratorKnowledge(businessType);
    }

    @PutMapping("/collaboratorknowledge/{id}")
    public CollaboratorKnowledge updateCollaboratorKnowledge(@RequestBody CollaboratorKnowledge newCollaboratorKnowledge, @PathVariable(value = "id") Integer id)
            throws ParseException {
        return collaboratorKnowledgeService.updateCollaboratorKnowledge(newCollaboratorKnowledge, id);
    }

    @PutMapping("/status/{id}")
    public CollaboratorKnowledge updateCollaboratorKnowledgeStatus(@RequestBody CollaboratorKnowledge businessType, @PathVariable(value = "id") Integer id)
            throws ParseException {
        return collaboratorKnowledgeService.updateCollaboratorKnowledgeStatus(id, businessType);
    }
}
