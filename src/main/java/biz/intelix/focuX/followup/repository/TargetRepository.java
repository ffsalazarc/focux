package biz.intelix.focuX.followup.repository;

import biz.intelix.focuX.followup.model.Target;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TargetRepository extends JpaRepository<Target, Integer> {

    Target findTargetById(Integer id);

}
