package biz.intelix.focuX.followup.repository;

import biz.intelix.focuX.followup.model.RequestPeriod;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RequestPeriodRepository extends JpaRepository<RequestPeriod, Integer> {

    RequestPeriod findRequestPeriodById(Integer id);
}
