package biz.intelix.focuX.followup.repository;

import biz.intelix.focuX.followup.model.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatusRepository extends JpaRepository<Status, Integer> {

    Status findStatusById(Integer id);
    Status findStatusByTypeStatusAndName(String typeStatus, String name);
}
