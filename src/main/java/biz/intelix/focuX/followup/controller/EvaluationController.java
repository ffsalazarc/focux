package biz.intelix.focuX.followup.controller;

import biz.intelix.focuX.followup.exception.ResourceNotFoundException;
import biz.intelix.focuX.followup.model.Evaluation;
import biz.intelix.focuX.followup.service.EvaluationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/followup/evaluation")
@CrossOrigin("*")
public class EvaluationController {

    private EvaluationService evaluationService;
    @Autowired
    public EvaluationController(EvaluationService evaluationService) {
        this.evaluationService = evaluationService;
    }

    @GetMapping("/all")
    public List<Evaluation> getAllEvaluation() {
        return evaluationService.getAllEvaluation();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Evaluation> findEvaluationById(@PathVariable(value = "id") Integer id)
            throws ResourceNotFoundException {
        return evaluationService.findEvaluationById(id);
    }

    @PostMapping("/save")
    public Evaluation createEvaluation(@RequestBody Evaluation evaluation)
            throws ParseException {
        return evaluationService.saveEvaluation(evaluation);
    }

    @PutMapping("/evaluation/{id}")
    public Evaluation updateEvaluation(@RequestBody Evaluation newEvaluation, @PathVariable(value = "id") Integer id)
            throws ParseException {
        return evaluationService.updateEvaluation(newEvaluation, id);
    }

    @PutMapping("/status/{id}")
    public ResponseEntity<Evaluation> updateEvaluationtStatus(@RequestBody Evaluation evaluation, @PathVariable Integer id)
            throws ParseException {
        return evaluationService.updateEvaluationStatus(id, evaluation);
    }
}
