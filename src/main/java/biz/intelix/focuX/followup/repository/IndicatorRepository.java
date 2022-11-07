package biz.intelix.focuX.followup.repository;

import biz.intelix.focuX.followup.model.Indicator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IndicatorRepository extends JpaRepository<Indicator, Integer> {

    Indicator findIndicatorById(Integer id);
}
