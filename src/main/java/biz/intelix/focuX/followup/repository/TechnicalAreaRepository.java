package biz.intelix.focuX.followup.repository;

import biz.intelix.focuX.followup.model.TechnicalArea;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TechnicalAreaRepository extends JpaRepository<TechnicalArea, Integer> {

    TechnicalArea findTechnicalAreaById(Integer id);
}
