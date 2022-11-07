package biz.intelix.focuX.followup.controller;


import biz.intelix.focuX.followup.exception.ResourceNotFoundException;
import biz.intelix.focuX.followup.model.Knowledge;
import biz.intelix.focuX.followup.service.KnowledgeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/followup/knowledges")
@CrossOrigin("*")
public class KnowledgeController {

    private KnowledgeService knowledgeService;

    @Autowired
    public KnowledgeController(KnowledgeService knowledgeService) {
        this.knowledgeService = knowledgeService;
    }

    @GetMapping("/all")
    public List<Knowledge> getAllKnowledges() {
        return knowledgeService.getAllKnowledges();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Knowledge> findKnowledgeById(@PathVariable(value = "id") Integer id)
            throws ResourceNotFoundException {
        return knowledgeService.findKnowledgeById(id);
    }

    @PostMapping("/save")
    public Knowledge createKnowledge(@RequestBody Knowledge Knowledge)
            throws ParseException {
        return knowledgeService.saveKnowledge(Knowledge);
    }

    @PutMapping("/knowledge/{id}")
    public Knowledge updateKnowledge(@RequestBody Knowledge newKnowledge, @PathVariable Integer id)
            throws ParseException {
        return knowledgeService.updateKnowledge(newKnowledge, id);
    }
    @PutMapping("/status/{id}")
    public Knowledge updateKnowledgeStatus(@RequestBody Knowledge knowledge, @PathVariable Integer id)
            throws ParseException {
        return knowledgeService.updateKnowledgeStatus(id, knowledge);
    }
}
