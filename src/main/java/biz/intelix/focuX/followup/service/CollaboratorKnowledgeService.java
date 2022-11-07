package biz.intelix.focuX.followup.service;

import biz.intelix.focuX.followup.model.CollaboratorKnowledge;
import biz.intelix.focuX.followup.repository.CollaboratorKnowledgeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CollaboratorKnowledgeService {

    private final CollaboratorKnowledgeRepository collaboratorKnowledgeRepository;

    @Autowired
    public CollaboratorKnowledgeService(CollaboratorKnowledgeRepository collaboratorKnowledgeRepository) {
        this.collaboratorKnowledgeRepository = collaboratorKnowledgeRepository;
    }

    public List<CollaboratorKnowledge> getAllCollaboratorKnowledges() {
        return collaboratorKnowledgeRepository.findAll();
    }

    public ResponseEntity<CollaboratorKnowledge> findCollaboratorKnowledgeById(Integer id) {
        CollaboratorKnowledge collaboratorKnowledge = collaboratorKnowledgeRepository.findCollaboratorKnowledgeById(id);
        return ResponseEntity.ok().body(collaboratorKnowledge);
    }

    public CollaboratorKnowledge saveCollaboratorKnowledge(CollaboratorKnowledge collaboratorKnowledge) {
        return collaboratorKnowledgeRepository.save(collaboratorKnowledge);
    }

    public CollaboratorKnowledge updateCollaboratorKnowledge(CollaboratorKnowledge newCollaboratorKnowledge, Integer id) {

        return collaboratorKnowledgeRepository.findById(id)
                .map(collaboratorKnowledge -> {
                    collaboratorKnowledge.setId(newCollaboratorKnowledge.getId());                    
                    collaboratorKnowledge.setCollaborator(newCollaboratorKnowledge.getCollaborator());
                    collaboratorKnowledge.setKnowledge(newCollaboratorKnowledge.getKnowledge());
                    collaboratorKnowledge.setUpdated(newCollaboratorKnowledge.getUpdated());
                    collaboratorKnowledge.setUpdatedBy(newCollaboratorKnowledge.getUpdatedBy());
                    return collaboratorKnowledgeRepository.save(collaboratorKnowledge);
                })
                .orElseGet(() -> {
                    newCollaboratorKnowledge.setId(id);
                    return collaboratorKnowledgeRepository.save(newCollaboratorKnowledge);
                });
    }

    public CollaboratorKnowledge updateCollaboratorKnowledgeStatus(Integer id, CollaboratorKnowledge collaboratorKnowledge) {

        return collaboratorKnowledgeRepository.findById(id)
                .map(collaboratorKnowledgeWithNewStatus -> {
                    collaboratorKnowledgeWithNewStatus.setIsActive(collaboratorKnowledge.getIsActive());
                    collaboratorKnowledgeWithNewStatus.setUpdated(collaboratorKnowledge.getUpdated());
                    collaboratorKnowledgeWithNewStatus.setUpdatedBy(collaboratorKnowledge.getUpdatedBy());
                    return collaboratorKnowledgeRepository.save(collaboratorKnowledgeWithNewStatus);
                }).get();

    }
}
