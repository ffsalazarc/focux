package biz.intelix.focuX.followup.repository;

import biz.intelix.focuX.followup.model.Client;
import biz.intelix.focuX.followup.model.Vacation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VacationRepository extends JpaRepository<Vacation, Integer> {

    Vacation findVacationByIdCollaborator(Integer id);
}
