package biz.intelix.focuX.followup.repository.implementation;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import biz.intelix.focuX.followup.model.Client;
import biz.intelix.focuX.followup.repository.DynamicClientRepository;

public class DynamicClientRepositoryImpl implements DynamicClientRepository {
    private final EntityManager entityManager;

    public DynamicClientRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Client> findClientsByBussinessType(List<Integer> bussinessTypes) {
        StringBuilder query = new StringBuilder("SELECT c from Client c ");

        if (bussinessTypes != null && !bussinessTypes.isEmpty()) query.append("WHERE c.businessType.id IN :bussinessTypes");
        Query q = entityManager.createQuery(query.toString(), Client.class);
        
        if(bussinessTypes != null && !bussinessTypes.isEmpty()) q.setParameter("bussinessTypes", bussinessTypes);
        
        return q.getResultList();
    }
}
