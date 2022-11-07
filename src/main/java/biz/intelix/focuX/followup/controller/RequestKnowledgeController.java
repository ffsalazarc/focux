package biz.intelix.focuX.followup.controller;

import biz.intelix.focuX.followup.exception.ResourceNotFoundException;
import biz.intelix.focuX.followup.model.RequestKnowledge;
import biz.intelix.focuX.followup.service.RequestKnowledgeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/followup/requestknowledge")
@CrossOrigin("*")
public class RequestKnowledgeController {

    private RequestKnowledgeService requestKnowledgeService;

    @Autowired
    public RequestKnowledgeController(RequestKnowledgeService requestKnowledgeService) {
        this.requestKnowledgeService = requestKnowledgeService;
    }

    @GetMapping("/all")
    public List<RequestKnowledge> getAllRequestKnowledge() {
        return requestKnowledgeService.getAllRequestKnowledges();
    }

    @GetMapping("/{id}")
    public ResponseEntity<RequestKnowledge> findRequestKnowledgeByIdFile(@PathVariable(value = "id") Integer id)
            throws ResourceNotFoundException {
        return requestKnowledgeService.findRequestKnowledgeById(id);
    }

    @PostMapping("/save")
    public RequestKnowledge createRequestKnowledge(@RequestBody RequestKnowledge businessType)
            throws ParseException {
        return requestKnowledgeService.saveRequestKnowledge(businessType);
    }

    @PutMapping("/requestknowledge/{id}")
    public RequestKnowledge updateRequestKnowledge(@RequestBody RequestKnowledge newRequestKnowledge, @PathVariable(value = "id") Integer id)
            throws ParseException {
        return requestKnowledgeService.updateRequestKnowledge(newRequestKnowledge, id);
    }

    @PutMapping("/status/{id}")
    public RequestKnowledge updateRequestKnowledgeStatus(@RequestBody RequestKnowledge businessType, @PathVariable(value = "id") Integer id)
            throws ParseException {
        return requestKnowledgeService.updateRequestKnowledgeStatus(id, businessType);
    }
}
