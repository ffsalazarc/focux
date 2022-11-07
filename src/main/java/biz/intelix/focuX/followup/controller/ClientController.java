package biz.intelix.focuX.followup.controller;

import biz.intelix.focuX.followup.exception.ResourceNotFoundException;
import biz.intelix.focuX.followup.model.Client;
import biz.intelix.focuX.followup.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/followup/clients")
@CrossOrigin("*")
public class ClientController {

    private ClientService clientService;

    @Autowired
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping("/all")
    public List<Client> getAllClient() {
        return clientService.getAllClients();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Client> findClientById(@PathVariable(value = "id") Integer id)
            throws ResourceNotFoundException {
        return clientService.findClientById(id);
    }

    @PostMapping("/save")
    public Client createClient(@RequestBody Client client)
            throws ParseException {
        return clientService.saveClient(client);
    }

    @PutMapping("/client/{id}")
    public Client updateClient(@RequestBody Client newClient, @PathVariable(value = "id") Integer id)
            throws ParseException {
        return clientService.updateClient(newClient, id);
    }

    @PutMapping("/status/{id}")
    public Client updateClientStatus(@RequestBody Client client, @PathVariable(value = "id")
     Integer id)
            throws ParseException {
        return clientService.updateClientStatus(id, client);
    }

    @GetMapping("/bussinesstype")
    @PreAuthorize("hasRole('ROLE_BASIC')")
    public List<Client> filterByBussinessTypes(@RequestParam(required = false) List<Integer> bussinessTypes) throws ParseException {
        return clientService.filterByBussinessType(bussinessTypes);
    }

}
