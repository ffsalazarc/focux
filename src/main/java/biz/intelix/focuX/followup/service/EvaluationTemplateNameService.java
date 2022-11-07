package biz.intelix.focuX.followup.service;
import biz.intelix.focuX.followup.model.EvaluationTemplate;
import biz.intelix.focuX.followup.model.EvaluationTemplateName;
import biz.intelix.focuX.followup.repository.EvaluationTemplateNameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;


@Service
public class EvaluationTemplateNameService {

    private final EvaluationTemplateNameRepository evaluationTemplateNameRepository;
    @Autowired
    public EvaluationTemplateNameService(EvaluationTemplateNameRepository evaluationTemplateNameRepository) {
        this.evaluationTemplateNameRepository = evaluationTemplateNameRepository;
    }

    public List<EvaluationTemplateName> getAllEvaluationTemplateName() {
        return evaluationTemplateNameRepository.findAll();
    }

    public ResponseEntity<EvaluationTemplateName> findEvaluationTemplateNameById(Integer id) {
        EvaluationTemplateName evaluationTemplateName = evaluationTemplateNameRepository.findEvaluationTemplateNameById(id);
        return ResponseEntity.ok().body(evaluationTemplateName);
    }

    public EvaluationTemplateName saveEvaluationTemplateName(EvaluationTemplateName evaluationTemplateName) {
        return evaluationTemplateNameRepository.save(evaluationTemplateName);
    }

    public EvaluationTemplateName updateEvaluationTemplateName(EvaluationTemplateName newEvaluationTemplateName, Integer id) {

        return evaluationTemplateNameRepository.findById(id)
                .map(templateName -> {
                    templateName.setId(newEvaluationTemplateName.getId());
                    templateName.setName(newEvaluationTemplateName.getName());
                    templateName.setCode(newEvaluationTemplateName.getCode());
                    templateName.setTemplates(newEvaluationTemplateName.getTemplates());
                    return evaluationTemplateNameRepository.save(templateName);
                })
                .orElseGet(() -> {
                    newEvaluationTemplateName.setId(id);
                    return evaluationTemplateNameRepository.save(newEvaluationTemplateName);
                });
    }
    
    public ResponseEntity<EvaluationTemplateName> updateEvaluationTemplateNameStatus(EvaluationTemplateName newEvaluationTemplateName, Integer id) {
        return evaluationTemplateNameRepository.findById(id)
                .map((oldTemplateName) -> {
                    oldTemplateName.setIsActive(newEvaluationTemplateName.getIsActive());
                    return ResponseEntity.ok().body(evaluationTemplateNameRepository.save(oldTemplateName));
                }).orElse(ResponseEntity.notFound().build());
    }

    @Transactional
    public ResponseEntity<EvaluationTemplateName> removeTemplates(List<EvaluationTemplate> templates, Integer id) {
        Optional<EvaluationTemplateName> templateNameOpt = evaluationTemplateNameRepository.findById(id);        
        if (templateNameOpt.isPresent()) {
            EvaluationTemplateName templateName = templateNameOpt.get();
            templateName.removeTemplates(templates);            
            return  ResponseEntity.ok().body(templateName);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
