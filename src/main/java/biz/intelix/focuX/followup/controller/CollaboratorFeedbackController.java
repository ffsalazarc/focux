package biz.intelix.focuX.followup.controller;

import biz.intelix.focuX.followup.exception.ResourceNotFoundException;
import biz.intelix.focuX.followup.model.CollaboratorFeedback;
import biz.intelix.focuX.followup.service.CollaboratorFeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/followup/collaboratorfedbacks")
@CrossOrigin("*")
public class CollaboratorFeedbackController {

    private CollaboratorFeedbackService collaboratorFeedbackService;

    @Autowired
    public CollaboratorFeedbackController(CollaboratorFeedbackService collaboratorFeedbackService) {
        this.collaboratorFeedbackService = collaboratorFeedbackService;
    }

    @GetMapping("/all")
    public List<CollaboratorFeedback> getAllCollaboratorFeedbacks() {
        return collaboratorFeedbackService.getAllCollaboratorFeedbacks();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CollaboratorFeedback> findCollaboratorFeedbackById(@PathVariable(value = "id") Integer id)
            throws ResourceNotFoundException {
        return collaboratorFeedbackService.findCollaboratorFeedbackById(id);
    }

    @PostMapping("/save")
    public CollaboratorFeedback createCollaboratorFeedback(@RequestBody CollaboratorFeedback collaboratorFeedback)
            throws ParseException {
        return collaboratorFeedbackService.saveCollaboratorFeedback(collaboratorFeedback);
    }

    @PutMapping("/collaboratorFeedback/{id}")
    public CollaboratorFeedback updateCollaboratorFeedback(@RequestBody CollaboratorFeedback newCollaboratorFeedback, @PathVariable(value = "id") Integer id)
            throws ParseException {
        return collaboratorFeedbackService.updateCollaboratorFeedback(newCollaboratorFeedback, id);
    }
    
}
