package biz.intelix.focuX.followup.controller;

import biz.intelix.focuX.followup.exception.ResourceNotFoundException;
import biz.intelix.focuX.followup.model.EvaluationTemplate;
import biz.intelix.focuX.followup.model.EvaluationTemplateName;
import biz.intelix.focuX.followup.service.EvaluationTemplateNameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/followup/evaluationtemplatename")
@CrossOrigin("*")
public class EvaluationTemplateNameController {

    private EvaluationTemplateNameService evaluationTemplateNameService;
    @Autowired
    public EvaluationTemplateNameController(EvaluationTemplateNameService evaluationTemplateNameService) {
        this.evaluationTemplateNameService = evaluationTemplateNameService;
    }

    @GetMapping("/all")
    public List<EvaluationTemplateName> getAllEvaluationTemplateName() {
        return evaluationTemplateNameService.getAllEvaluationTemplateName();
    }

    @GetMapping("/{id}")
    public ResponseEntity<EvaluationTemplateName> findEvaluationTemplateNameById(@PathVariable(value = "id") Integer id)
            throws ResourceNotFoundException {
        return evaluationTemplateNameService.findEvaluationTemplateNameById(id);
    }

    @PostMapping("/save")
    public EvaluationTemplateName createEvaluationTemplateName(@RequestBody EvaluationTemplateName evaluationTemplateName)
            throws ParseException {
        return evaluationTemplateNameService.saveEvaluationTemplateName(evaluationTemplateName);
    }

    @PutMapping("/evaluationtemplatename/{id}")
    public EvaluationTemplateName updateEvaluationTemplateName(@RequestBody EvaluationTemplateName newEvaluationTemplateName, @PathVariable(value = "id") Integer id)
            throws ParseException {
        return evaluationTemplateNameService.updateEvaluationTemplateName(newEvaluationTemplateName, id);
    }

    // Elimina una lista de EvaluationTemplate de un name
    @PostMapping("/remove/{id}")
    public ResponseEntity<EvaluationTemplateName> removeTemplates(@RequestBody List<EvaluationTemplate> templates, @PathVariable(value = "id") Integer id)
            throws ParseException {
        return evaluationTemplateNameService.removeTemplates(templates, id);
    }

    @PutMapping("/status/{id}")
    public ResponseEntity<EvaluationTemplateName> updateStatusTemplateName(@PathVariable(value = "id") Integer id, @RequestBody EvaluationTemplateName evaluationTemplateName) throws ParseException{
        return evaluationTemplateNameService.updateEvaluationTemplateNameStatus(evaluationTemplateName, id);
    }
}
