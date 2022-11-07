package biz.intelix.focuX.followup.repository;

import biz.intelix.focuX.followup.model.RequestKnowledge;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RequestKnowledgeRepository extends JpaRepository<RequestKnowledge, Integer> {

    RequestKnowledge findRequestKnowledgeById(Integer id);
}