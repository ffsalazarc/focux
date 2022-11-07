package biz.intelix.focuX.followup.repository;

import biz.intelix.focuX.followup.model.Evaluation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EvaluationRepository extends JpaRepository<Evaluation, Integer> {

    Evaluation findEvaluationById(Integer id);
}
