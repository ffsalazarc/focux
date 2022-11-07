package biz.intelix.focuX.followup.repository;

import biz.intelix.focuX.followup.model.Request;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RequestRepository extends JpaRepository<Request, Integer>, DashboardRepository {

    Request findRequestById(Integer id);

    List<Request> findRequestByClient_id(Integer id);
    List<Request> findRequestByClient_idAndStatus_id(Integer clientId, Integer statusId);
    List<Request> findRequestByResponsibleRequest_id(Integer id);
    List<Request> findRequestByResponsibleRequest_idAndStatus_id(Integer responsibleId, Integer statusId);
    List<Request> findRequestByStatus_Id(Integer statusId);
}
