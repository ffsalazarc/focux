package biz.intelix.focuX.followup.repository;

import biz.intelix.focuX.followup.model.BusinessType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BusinessTypeRepository extends JpaRepository<BusinessType, Integer> {

    BusinessType findBusinessTypeById(Integer id);
}
