package biz.intelix.focuX.followup.repository;

import biz.intelix.focuX.followup.model.RequestRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RequestRoleRepository extends JpaRepository<RequestRole, Integer> {

    RequestRole findRequestRoleById(Integer id);
}
