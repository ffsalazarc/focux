package biz.intelix.focuX.followup.repository;

import biz.intelix.focuX.followup.model.TypeRequest;
import biz.intelix.focuX.followup.model.TypeStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TypeStatusRepository extends JpaRepository<TypeStatus, Integer> {

    TypeStatus findTypeStatusById(Integer id);
}
