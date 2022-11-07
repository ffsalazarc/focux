package biz.intelix.focuX.followup.repository;

import java.util.List;

import biz.intelix.focuX.followup.model.Client;

public interface DynamicClientRepository {
    List<Client> findClientsByBussinessType(List<Integer> bussinessTypes);
}
