package biz.intelix.focuX.followup.repository;

import biz.intelix.focuX.followup.model.Knowledge;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KnowledgeRepository extends JpaRepository<Knowledge, Integer> {

    Knowledge findKnowledgeById(Integer id);
}
