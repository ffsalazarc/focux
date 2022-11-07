package biz.intelix.focuX.followup.controller;

import biz.intelix.focuX.followup.exception.ResourceNotFoundException;
import biz.intelix.focuX.followup.model.EvaluationTemplate;
import biz.intelix.focuX.followup.service.EvaluationTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/followup/evaluationtemplate")
@CrossOrigin("*")
public class EvaluationTemplateController {

    private EvaluationTemplateService evaluationTemplateService;
    @Autowired
    public EvaluationTemplateController(EvaluationTemplateService evaluationTemplateService) {
        this.evaluationTemplateService = evaluationTemplateService;
    }

    @GetMapping("/all")
    public List<EvaluationTemplate> getAllEvaluationTemplate() {
        return evaluationTemplateService.getAllEvaluationTemplate();
    }

    @GetMapping("/{id}")
    public ResponseEntity<EvaluationTemplate> findEvaluationTemplateById(@PathVariable(value = "id") Integer id)
            throws ResourceNotFoundException {
        return evaluationTemplateService.findEvaluationTemplateById(id);
    }

    @PostMapping("/save")
    public EvaluationTemplate createEvaluationTemplate(@RequestBody EvaluationTemplate evaluationTemplate)
            throws ParseException {
        return evaluationTemplateService.saveEvaluationTemplate(evaluationTemplate);
    }

    @PutMapping("/evaluationtemplate/{id}")
    public EvaluationTemplate updateEvaluationTemplate(@RequestBody EvaluationTemplate newEvaluationTemplate, @PathVariable(value = "id") Integer id)
            throws ParseException {
        return evaluationTemplateService.updateEvaluationTemplate(newEvaluationTemplate, id);
    }
}
