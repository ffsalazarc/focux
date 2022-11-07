package biz.intelix.focuX.followup.repository;

import biz.intelix.focuX.followup.model.CommercialArea;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommercialAreaRepository extends JpaRepository <CommercialArea, Integer> {

    CommercialArea findCommercialAreaById(Integer id);
}
