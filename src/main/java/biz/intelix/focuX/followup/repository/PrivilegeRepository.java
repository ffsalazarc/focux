package biz.intelix.focuX.followup.repository;

import biz.intelix.focuX.followup.model.Privilege;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PrivilegeRepository extends JpaRepository<Privilege, Long> {
    List<Privilege> findAll();
    Privilege findByName(String name);
}
