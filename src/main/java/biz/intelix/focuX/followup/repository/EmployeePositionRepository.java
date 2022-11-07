package biz.intelix.focuX.followup.repository;

import biz.intelix.focuX.followup.model.EmployeePosition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeePositionRepository extends JpaRepository<EmployeePosition, Integer> {

    EmployeePosition findEmployeePositionById(Integer id);
}
