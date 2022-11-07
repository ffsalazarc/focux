package biz.intelix.focuX.followup.controller;

import biz.intelix.focuX.followup.exception.ResourceNotFoundException;
import biz.intelix.focuX.followup.model.EvaluationPerformance;
import biz.intelix.focuX.followup.model.EvaluationResultDTO;
import biz.intelix.focuX.followup.model.dto.CollaboratorEvaluationDTO;
import biz.intelix.focuX.followup.service.EvaluationPerformanceService;
import biz.intelix.focuX.followup.service.EvaluationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.annotation.RequestScope;

import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/followup/evaluationperformance")
@CrossOrigin("*")
public class EvaluationPerformanceController {

    private EvaluationPerformanceService evaluationPerformanceService;
    @Autowired
    public EvaluationPerformanceController(EvaluationPerformanceService evaluationPerformanceService) {
        this.evaluationPerformanceService = evaluationPerformanceService;
    }

    @GetMapping("/all")
    public List<EvaluationPerformance> getAllEvaluationPerformance() {
        return evaluationPerformanceService.getAllEvaluationPerformance();
    }

    @GetMapping("/{id}")
    public ResponseEntity<EvaluationPerformance> findEvaluationPerformanceById(@PathVariable(value = "id") Integer id)
            throws ResourceNotFoundException {
        return evaluationPerformanceService.findEvaluationPerformanceById(id);
    }

    @PostMapping("/save")
    public EvaluationPerformance createEvaluationPerformance(@RequestBody EvaluationPerformance evaluationPerformance)
            throws ParseException {
        return evaluationPerformanceService.saveEvaluationPerformance(evaluationPerformance);
    }

    // TODO: ¿se pueden editar?
    @PutMapping("/evaluationperformance/{id}")
    public EvaluationPerformance updateEvaluationPerformance(@RequestBody EvaluationPerformance newEvaluationPerformance, @PathVariable(value = "id") Integer id)
            throws ParseException {
        return evaluationPerformanceService.updateEvaluationPerformance(newEvaluationPerformance, id);
    }

    @GetMapping("/collaboratorsevaluated")
    public List<CollaboratorEvaluationDTO> getCollaboratorsEvaluationDTO(@RequestParam Integer year, @RequestParam Integer period, @RequestParam(required = false) Integer deparmentId) {
        return evaluationPerformanceService.getCollaboratorsEvaluationDTO(year, period, deparmentId);
    }

    @PostMapping("/saveall")
    public List<EvaluationPerformance> saveAllEvaluationPerformance(@RequestBody List<EvaluationPerformance> evaluations) {
        return evaluationPerformanceService.saveAllEvaluationPerformance(evaluations);
    }

    @GetMapping("/results")
    public ResponseEntity<EvaluationResultDTO> getEvaluationResult(@RequestParam Integer evaluatedId, @RequestParam Integer year, @RequestParam Integer period) {
        return evaluationPerformanceService.getEvaluationResult(evaluatedId, year, period);
    }
}
