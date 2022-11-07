package biz.intelix.focuX.followup.repository;

import biz.intelix.focuX.followup.model.EvaluationTemplateName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EvaluationTemplateNameRepository extends JpaRepository<EvaluationTemplateName, Integer> {

    EvaluationTemplateName findEvaluationTemplateNameById(Integer id);
}
