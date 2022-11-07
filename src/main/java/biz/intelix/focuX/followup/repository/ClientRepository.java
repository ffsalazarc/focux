package biz.intelix.focuX.followup.repository;

import biz.intelix.focuX.followup.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client, Integer>, DynamicClientRepository {

    Client findClientById(Integer id);
}
