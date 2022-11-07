package biz.intelix.focuX.followup.repository;

import biz.intelix.focuX.followup.model.TypeRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TypeRequestRepository extends JpaRepository<TypeRequest, Integer> {

    TypeRequest findTypeRequestById(Integer id);
}
