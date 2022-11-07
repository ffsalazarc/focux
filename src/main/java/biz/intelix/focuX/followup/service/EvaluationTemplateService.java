package biz.intelix.focuX.followup.service;
import biz.intelix.focuX.followup.model.EvaluationTemplate;
import biz.intelix.focuX.followup.repository.EvaluationTemplateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
public class EvaluationTemplateService {

    private final EvaluationTemplateRepository evaluationTemplateRepository;

    @Autowired
    public EvaluationTemplateService(EvaluationTemplateRepository evaluationTemplateRepository) {
        this.evaluationTemplateRepository = evaluationTemplateRepository;
    }

    public List<EvaluationTemplate> getAllEvaluationTemplate() {
        return evaluationTemplateRepository.findAll();
    }

    public ResponseEntity<EvaluationTemplate> findEvaluationTemplateById(Integer id) {
        EvaluationTemplate evaluationTemplate = evaluationTemplateRepository.findEvaluationTemplateById(id);
        return ResponseEntity.ok().body(evaluationTemplate);
    }

    public EvaluationTemplate saveEvaluationTemplate(EvaluationTemplate evaluationTemplate) {
        return evaluationTemplateRepository.save(evaluationTemplate);
    }

    public EvaluationTemplate updateEvaluationTemplate(EvaluationTemplate newEvaluationTemplate, Integer id) {

        return evaluationTemplateRepository.findById(id)
                .map(template -> {
                    template.setId(newEvaluationTemplate.getId());
                    template.setEvaluationTemplateName(newEvaluationTemplate.getEvaluationTemplateName());
                    template.setEvaluation(newEvaluationTemplate.getEvaluation());                    
                    return evaluationTemplateRepository.save(template);
                })
                .orElseGet(() -> {
                    newEvaluationTemplate.setId(id);
                    return evaluationTemplateRepository.save(newEvaluationTemplate);
                });
    }
}
