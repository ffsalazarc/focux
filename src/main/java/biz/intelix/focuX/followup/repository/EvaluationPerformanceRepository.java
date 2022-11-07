package biz.intelix.focuX.followup.repository;

import biz.intelix.focuX.followup.model.EvaluationPerformance;

import java.util.List;

import javax.persistence.Tuple;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface EvaluationPerformanceRepository extends JpaRepository<EvaluationPerformance, Integer> {

    EvaluationPerformance findEvaluationPerformanceById(Integer id);

    @Query(nativeQuery = true, value = "SELECT c.id, c.name as collab_name, c.last_name as collab_last_name, cli.name as client, emp.name as position ,CASE WHEN ep.id_evaluated is NULL THEN 0 ELSE 1 END as is_evaluated\n" +
                                        "FROM \"FOLLOWUP\".\"CAT_COLLABORATOR\" as c\n" +
                                        "INNER JOIN \"FOLLOWUP\".\"CAT_CLIENT\" as cli\n" +
                                        "ON cli.id = c.id_client\n" +
                                        "INNER JOIN \"FOLLOWUP\".\"CAT_EMPLOYEE_POSITION\" as emp\n" +
                                        "ON emp.id = c.id_employee_position\n" +
                                        "LEFT JOIN \"FOLLOWUP\".\"EVALUATION_PERFORMANCE\" as ep\n" +
                                        "ON c.id = ep.id_evaluated AND :year = ep.year and :period = ep.period\n" +
                                        "WHERE emp.id_department = :departmentId\n" +
                                        "ORDER BY c.name || ' ' || c.last_name ASC\n")
    List<Tuple> findIfCollaboratorIsEvaluatedInPeriod(Integer year, Integer period, Integer departmentId);

    List<EvaluationPerformance> findByEvaluated_Id(Integer id);
    @Query(value = "SELECT ep FROM EvaluationPerformance ep WHERE ep.evaluated.id = :evaluatedId AND ep.year = :year AND period = :period")
    List<EvaluationPerformance> findByEvaluatedIdAndPeriodAndYear(Integer evaluatedId, Integer year, Integer period);
}
