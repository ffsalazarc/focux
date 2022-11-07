package biz.intelix.focuX.followup.repository;

import biz.intelix.focuX.followup.model.EvaluationTemplate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EvaluationTemplateRepository extends JpaRepository<EvaluationTemplate, Integer> {

    EvaluationTemplate findEvaluationTemplateById(Integer id);
}
