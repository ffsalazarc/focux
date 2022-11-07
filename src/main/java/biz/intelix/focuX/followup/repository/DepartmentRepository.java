package biz.intelix.focuX.followup.repository;

import biz.intelix.focuX.followup.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Integer> {

    Department findDepartmentById(Integer id);

}
