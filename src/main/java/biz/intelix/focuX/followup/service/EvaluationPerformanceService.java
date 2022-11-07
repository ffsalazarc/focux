package biz.intelix.focuX.followup.service;
import biz.intelix.focuX.followup.model.Evaluation;
import biz.intelix.focuX.followup.model.EvaluationPerformance;
import biz.intelix.focuX.followup.model.EvaluationResultDTO;
import biz.intelix.focuX.followup.model.dto.CollaboratorEvaluationDTO;
import biz.intelix.focuX.followup.repository.EvaluationPerformanceRepository;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Tuple;


@Service
public class EvaluationPerformanceService {

    private static final int HashMap = 0;
    private final EvaluationPerformanceRepository evaluationPerformanceRepository;

    @Autowired
    public EvaluationPerformanceService(EvaluationPerformanceRepository evaluationPerformanceRepository) {
        this.evaluationPerformanceRepository = evaluationPerformanceRepository;
    }

    public List<EvaluationPerformance> getAllEvaluationPerformance() {
        return evaluationPerformanceRepository.findAll();
    }

    public ResponseEntity<EvaluationPerformance> findEvaluationPerformanceById(Integer id) {
        EvaluationPerformance evaluationPerformance = evaluationPerformanceRepository.findEvaluationPerformanceById(id);
        return ResponseEntity.ok().body(evaluationPerformance);
    }

    public EvaluationPerformance saveEvaluationPerformance(EvaluationPerformance evaluationPerformance) {
        return evaluationPerformanceRepository.save(evaluationPerformance);
    }

    public List<EvaluationPerformance> saveAllEvaluationPerformance(List<EvaluationPerformance> evaluations) {        
        /* Diccionario con los resultados de la evaluación cada mes
           Ejemplo: 1 -> 100,
                    2 -> 65,
                    3 -> 100
            Luego se calcula el promedio de cada mes para dar el resultado de la evaluación del trimestre.
            Siguiendo el ejemplo anterior su promedio sería 88.333
        */
        Map<Integer, Double> resultPerMonth = new HashMap<>();
        for (EvaluationPerformance evaluation : evaluations) {
            addMonthToMap(resultPerMonth, evaluation);
        }
        for (Map.Entry<Integer, Double> entry : resultPerMonth.entrySet()) {
            if (entry.getValue() > 100) {                
                throw new IllegalArgumentException("El mes " + entry.getKey() + "excede 100% en la evaluacion. Tiene " + entry.getValue());
            }
        }
        
        return evaluationPerformanceRepository.saveAll(evaluations);
    }

    public ResponseEntity<EvaluationResultDTO> getEvaluationResult(Integer evaluatedId, Integer year, Integer period) {
        List<EvaluationPerformance> evaluations = evaluationPerformanceRepository.findByEvaluatedIdAndPeriodAndYear (evaluatedId, year, period);
        Double totalResult = 0.0;
        Map<Integer, Double> resultPerMonth = new HashMap<>();
        Map<String, Map<Integer, Double>> objectives = new HashMap<>();
        for (EvaluationPerformance evaluation: evaluations) {
            System.out.println(evaluation);
            addMonthToMap(resultPerMonth, evaluation);
            String targetName = evaluation.getEvaluation().getTarget().getName();
            if (objectives.containsKey(targetName)) {
                Map<Integer, Double> objectiveTotalPerMonth = objectives.get(targetName);
                addMonthToMap(objectiveTotalPerMonth, evaluation);
            } else {
                Map<Integer, Double> objectiveTotalPerMonth = new HashMap<>();
                objectiveTotalPerMonth.put(evaluation.getMonth(), Double.valueOf(evaluation.getEvaluationResult()));
                objectives.put(targetName, objectiveTotalPerMonth);
            }
        }
        for (Map.Entry<Integer, Double> entry : resultPerMonth.entrySet()) {
            totalResult += entry.getValue();
        }
        totalResult = totalResult/resultPerMonth.size();
        EvaluationResultDTO result = new EvaluationResultDTO(totalResult, resultPerMonth, objectives, period, evaluatedId, year);
        return ResponseEntity.ok(result);
    }

    private void addMonthToMap(Map<Integer, Double> map, EvaluationPerformance evaluationPerformance) {
        Double result;
        Evaluation evaluation = evaluationPerformance.getEvaluation();
        if (evaluation.getTarget().getType() == "Ascendente") {
            result = evaluationPerformance.getEvaluationResult() / Double.valueOf(evaluation.getMaximumPercentage());
        } else {
            result = 1 - (evaluationPerformance.getEvaluationResult()/Double.valueOf(evaluation.getMinimumPercentage()));
        }
        result = result*evaluationPerformance.getWeight();
        if (map.containsKey(evaluationPerformance.getMonth())) {            
            map.put(evaluationPerformance.getMonth(), map.get(evaluationPerformance.getMonth()) + result);
        } else {
            map.put(evaluationPerformance.getMonth(), Double.valueOf(evaluationPerformance.getEvaluationResult()));
        }
    }

    public EvaluationPerformance updateEvaluationPerformance(EvaluationPerformance newEvaluationPerformance, Integer id) {

        return evaluationPerformanceRepository.findById(id)
                .map(evaluationPerformance -> {
                    evaluationPerformance.setId(newEvaluationPerformance.getId());
                    evaluationPerformance.setEvaluated(newEvaluationPerformance.getEvaluator());
                    evaluationPerformance.setEvaluator(newEvaluationPerformance.getEvaluator());
                    evaluationPerformance.setEvaluationDate(newEvaluationPerformance.getEvaluationDate());
                    evaluationPerformance.setEvaluationResult(newEvaluationPerformance.getEvaluationResult());
                    evaluationPerformance.setPeriod(newEvaluationPerformance.getPeriod());
                    evaluationPerformance.setEvaluation(newEvaluationPerformance.getEvaluation());
                    evaluationPerformance.setWeight(newEvaluationPerformance.getWeight());
                    evaluationPerformance.setMonth(newEvaluationPerformance.getMonth());
                    return evaluationPerformanceRepository.save(evaluationPerformance);
                })
                .orElseGet(() -> {
                    newEvaluationPerformance.setId(id);
                    return evaluationPerformanceRepository.save(newEvaluationPerformance);
                });
    }

    public List<CollaboratorEvaluationDTO> getCollaboratorsEvaluationDTO(Integer year, Integer period, Integer departmentId) {
       if (departmentId == null) departmentId = 1;
        List<Tuple> collabInfo = evaluationPerformanceRepository.findIfCollaboratorIsEvaluatedInPeriod(year, period, departmentId);
       
       List<CollaboratorEvaluationDTO> collaboratorWithEvaluations = new ArrayList<>();
       for(Tuple tuple : collabInfo) {
            Integer id = (Integer) tuple.get("id");
            String name = tuple.get("collab_name").toString();
            String lastName = tuple.get("collab_last_name").toString();
            String client = tuple.get("client").toString();
            Integer isEvaluated = (Integer) tuple.get("is_evaluated");
            String position = tuple.get("position").toString();
            List<EvaluationPerformance> evaluations = evaluationPerformanceRepository.findByEvaluated_Id(id);
            CollaboratorEvaluationDTO collabEval = new CollaboratorEvaluationDTO(id, name, lastName, client, isEvaluated, position, evaluations);
            collaboratorWithEvaluations.add(collabEval);
       }        
        return collaboratorWithEvaluations;
    }
}
