package biz.intelix.focuX.followup.repository;

import biz.intelix.focuX.followup.model.CollaboratorFeedback;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface CollaboratorFeedbackRepository extends JpaRepository<CollaboratorFeedback, Integer> {

    CollaboratorFeedback findCollaboratorFeedbackById(Integer id);
    
}
