package biz.intelix.focuX.followup.repository;

import biz.intelix.focuX.followup.model.CollaboratorKnowledge;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CollaboratorKnowledgeRepository extends JpaRepository<CollaboratorKnowledge, Integer> {

    CollaboratorKnowledge findCollaboratorKnowledgeById(Integer id);
}