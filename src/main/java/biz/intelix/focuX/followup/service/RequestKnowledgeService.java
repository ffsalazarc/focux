package biz.intelix.focuX.followup.service;

import biz.intelix.focuX.followup.model.RequestKnowledge;
import biz.intelix.focuX.followup.repository.RequestKnowledgeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RequestKnowledgeService {

    private final RequestKnowledgeRepository requestKnowledgeRepository;

    @Autowired
    public RequestKnowledgeService(RequestKnowledgeRepository requestKnowledgeRepository) {
        this.requestKnowledgeRepository = requestKnowledgeRepository;
    }

    public List<RequestKnowledge> getAllRequestKnowledges() {
        return requestKnowledgeRepository.findAll();
    }

    public ResponseEntity<RequestKnowledge> findRequestKnowledgeById(Integer id) {
        RequestKnowledge requestKnowledge = requestKnowledgeRepository.findRequestKnowledgeById(id);
        return ResponseEntity.ok().body(requestKnowledge);
    }

    public RequestKnowledge saveRequestKnowledge(RequestKnowledge requestKnowledge) {
        return requestKnowledgeRepository.save(requestKnowledge);
    }

    public RequestKnowledge updateRequestKnowledge(RequestKnowledge newRequestKnowledge, Integer id) {

        return requestKnowledgeRepository.findById(id)
                .map(requestKnowledge -> {
                    requestKnowledge.setId(newRequestKnowledge.getId());                    
                    requestKnowledge.setRequest(newRequestKnowledge.getRequest());
                    requestKnowledge.setKnowledge(newRequestKnowledge.getKnowledge());
                    return requestKnowledgeRepository.save(requestKnowledge);
                })
                .orElseGet(() -> {
                    newRequestKnowledge.setId(id);
                    return requestKnowledgeRepository.save(newRequestKnowledge);
                });
    }

    public RequestKnowledge updateRequestKnowledgeStatus(Integer id, RequestKnowledge requestKnowledge) {

        return requestKnowledgeRepository.findById(id)
                .map(requestKnowledgeWithNewStatus -> {
                    requestKnowledgeWithNewStatus.setIsActive(requestKnowledge.getIsActive());
                    return requestKnowledgeRepository.save(requestKnowledgeWithNewStatus);
                }).get();

    }
}
