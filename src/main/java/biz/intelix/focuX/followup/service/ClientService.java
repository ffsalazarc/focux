package biz.intelix.focuX.followup.service;

import biz.intelix.focuX.followup.model.Client;
import biz.intelix.focuX.followup.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService {

    private final ClientRepository clientRepository;

    @Autowired
    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }

    public ResponseEntity<Client> findClientById(Integer id) {
        Client client = clientRepository.findClientById(id);
        return ResponseEntity.ok().body(client);
    }

    public Client saveClient(Client client) {
        return clientRepository.save(client);
    }

    public Client updateClient(Client newClient, Integer id) {

        return clientRepository.findById(id)
                .map(client -> {
                    client.setId(newClient.getId());
                    client.setBusinessType(newClient.getBusinessType());
                    client.setName(newClient.getName());
                    client.setDescription(newClient.getDescription());
                    client.setUpdated(newClient.getUpdated());
                    client.setUpdatedBy(newClient.getUpdatedBy());
                    return clientRepository.save(client);
                })
                .orElseGet(() -> {
                    newClient.setId(id);
                    return clientRepository.save(newClient);
                });
    }

    public Client updateClientStatus(Integer id, Client client) {

        return clientRepository.findById(id)
                .map(clientWithNewStatus -> {
                    clientWithNewStatus.setIsActive(client.getIsActive());
                    clientWithNewStatus.setUpdated(client.getUpdated());
                    clientWithNewStatus.setUpdatedBy(client.getUpdatedBy());
                    return clientRepository.save(clientWithNewStatus);
                }).get();

    }

    public List<Client> filterByBussinessType(List<Integer> bussinessTypes) {
        return clientRepository.findClientsByBussinessType(bussinessTypes);
    }

}
