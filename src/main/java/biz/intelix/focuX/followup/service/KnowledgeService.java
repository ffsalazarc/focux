package biz.intelix.focuX.followup.service;

import biz.intelix.focuX.followup.model.Knowledge;
import biz.intelix.focuX.followup.repository.KnowledgeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KnowledgeService {

    private final KnowledgeRepository knowledgeRepository;

    @Autowired
    public KnowledgeService(KnowledgeRepository knowledgeRepository) {
        this.knowledgeRepository = knowledgeRepository;
    }

    public List<Knowledge> getAllKnowledges() {
        return knowledgeRepository.findAll();
    }

    public ResponseEntity<Knowledge> findKnowledgeById(Integer id) {
        Knowledge knowledge = knowledgeRepository.findKnowledgeById(id);
        return ResponseEntity.ok().body(knowledge);
    }

    public Knowledge saveKnowledge(Knowledge knowledge) {
        return knowledgeRepository.save(knowledge);
    }

    public Knowledge updateKnowledge(Knowledge newKnowledge, Integer id) {

        return knowledgeRepository.findById(id)
                .map(knowledge -> {
                    knowledge.setId(newKnowledge.getId());
                    knowledge.setType(newKnowledge.getType());
                    knowledge.setName(newKnowledge.getName());
                    knowledge.setDescription(newKnowledge.getDescription());
                    return knowledgeRepository.save(knowledge);
                })
                .orElseGet(() -> {
                    newKnowledge.setId(id);
                    return knowledgeRepository.save(newKnowledge);
                });
    }

    public Knowledge updateKnowledgeStatus(Integer id, Knowledge knowledge) {

        return knowledgeRepository.findById(id)
                .map(knowledgeWithNewStatus -> {
                    knowledgeWithNewStatus.setIsActive(knowledge.getIsActive());
                    return knowledgeRepository.save(knowledgeWithNewStatus);
                }).get();

    }
}
