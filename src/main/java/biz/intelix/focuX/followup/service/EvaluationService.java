package biz.intelix.focuX.followup.service;
import biz.intelix.focuX.followup.model.Evaluation;
import biz.intelix.focuX.followup.repository.EvaluationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;


@Service
public class EvaluationService {

    private final EvaluationRepository evaluationRepository;

    @Autowired
    public EvaluationService(EvaluationRepository evaluationRepository) {
        this.evaluationRepository = evaluationRepository;
    }

    public List<Evaluation> getAllEvaluation() {
        return evaluationRepository.findAll();
    }

    public ResponseEntity<Evaluation> findEvaluationById(Integer id) {
        Evaluation evaluation = evaluationRepository.findEvaluationById(id);
        return ResponseEntity.ok().body(evaluation);
    }

    public Evaluation saveEvaluation(Evaluation evaluation) {
        return evaluationRepository.save(evaluation);
    }

    public Evaluation updateEvaluation(Evaluation newEvaluation, Integer id) {

        return evaluationRepository.findById(id)
                .map(evaluation -> {
                    evaluation.setId(newEvaluation.getId());
                    evaluation.setMinimumPercentage(newEvaluation.getMinimumPercentage());
                    evaluation.setMaximumPercentage(newEvaluation.getMaximumPercentage());
                    evaluation.setTarget(newEvaluation.getTarget());
                    evaluation.setIndicator(newEvaluation.getIndicator());
                    evaluation.setName(newEvaluation.getName());
                    evaluation.setCode(newEvaluation.getCode());
                    return evaluationRepository.save(evaluation);
                })
                .orElseGet(() -> {
                    newEvaluation.setId(id);
                    return evaluationRepository.save(newEvaluation);
                });
    }

    public ResponseEntity<Evaluation> updateEvaluationStatus(Integer id, Evaluation newEvaluation) {
        return evaluationRepository.findById(id).
            map(evaluation -> {
                evaluation.setIsActive(newEvaluation.getIsActive());                
                return ResponseEntity.ok().body(evaluationRepository.save(evaluation));
            })
            .orElseGet(() -> {
                return   ResponseEntity.notFound().build();
            });
    }
}
