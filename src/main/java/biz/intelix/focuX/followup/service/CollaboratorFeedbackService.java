package biz.intelix.focuX.followup.service;

import biz.intelix.focuX.followup.model.CollaboratorFeedback;
import biz.intelix.focuX.followup.repository.CollaboratorFeedbackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CollaboratorFeedbackService {
    
    private final CollaboratorFeedbackRepository collaboratorFeedbackRepository;

    @Autowired
    public CollaboratorFeedbackService(CollaboratorFeedbackRepository collaboratorFeedbackRepository) {
        this.collaboratorFeedbackRepository = collaboratorFeedbackRepository;
    }

    public List<CollaboratorFeedback> getAllCollaboratorFeedbacks() {
        return collaboratorFeedbackRepository.findAll();
    }

    public ResponseEntity<CollaboratorFeedback> findCollaboratorFeedbackById(Integer id) {
        CollaboratorFeedback collaboratorFeedback = collaboratorFeedbackRepository.findCollaboratorFeedbackById(id);
        return ResponseEntity.ok().body(collaboratorFeedback);
    }

    public CollaboratorFeedback saveCollaboratorFeedback(CollaboratorFeedback collaboratorFeedback) {
        return collaboratorFeedbackRepository.save(collaboratorFeedback);
    }

    public CollaboratorFeedback updateCollaboratorFeedback(CollaboratorFeedback newFeedback, Integer id) {

        return collaboratorFeedbackRepository.findById(id)
                .map(client -> {
                    client.setId(newFeedback.getId());
                    client.setFeedbackPoints(newFeedback.getFeedbackPoints());
                    client.setReasonFeedback(newFeedback.getReasonFeedback());
                    client.setDescription(newFeedback.getDescription());
                    client.setAggrements(newFeedback.getAggrements());
                    client.setEndDateAggrements(newFeedback.getEndDateAggrements());
                    client.setEvaluatedObservations(newFeedback.getEvaluatedObservations());
                    client.setEvaluaterObservations(newFeedback.getEvaluaterObservations());
                    client.setResponsible(newFeedback.getResponsible());
                    return collaboratorFeedbackRepository.save(client);
                })
                .orElseGet(() -> {
                    newFeedback.setId(id);
                    return collaboratorFeedbackRepository.save(newFeedback);
                });
    }
}
