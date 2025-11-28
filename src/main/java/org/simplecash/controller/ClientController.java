package org.simplecash.controller;

import org.simplecash.entity.Client;
import org.simplecash.entity.CompteCourant;
import org.simplecash.entity.CompteEpargne;
import org.simplecash.service.ClientService;
import org.simplecash.service.CompteService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clients")
public class ClientController {

    private final ClientService clientService;
    private final CompteService compteService;

    public ClientController(ClientService clientService, CompteService compteService) {
        this.clientService = clientService;
        this.compteService = compteService;
    }

    // 🔵 CREATE client
    @PostMapping
    public Client createClient(@RequestBody Client client) {
        return clientService.create(client);
    }

    // 🔵 READ client
    @GetMapping("/{id}")
    public Client getClient(@PathVariable Long id) {
        return clientService.get(id);
    }

    // 🔵 LIST clients
    @GetMapping
    public List<Client> getAllClients() {
        return clientService.getAll();
    }

    // 🔵 UPDATE client
    @PutMapping("/{id}")
    public Client updateClient(@PathVariable Long id, @RequestBody Client client) {
        return clientService.update(id, client);
    }

    // 🔵 DELETE client
    @DeleteMapping("/{id}")
    public void deleteClient(@PathVariable Long id) {
        clientService.delete(id);
    }

    @PostMapping("/{id}/comptes")
    public Object createCompte(
            @PathVariable Long id,
            @RequestParam int type
    ) {
        switch (type) {
            case 1:
                return compteService.createCompteCourant(id);
            case 2:
                return compteService.createCompteEpargne(id);
            default:
                throw new RuntimeException("Type de compte invalide. Utilisez 1 (Courant) ou 2 (Épargne).");
        }
    }
    @GetMapping("/{id}/comptes")
    public Object getClientAccounts(@PathVariable Long id) {
        Client client = clientService.get(id);

        return new Object() {
            public final Object compteCourant = client.getCompteCourant();
            public final Object compteEpargne = client.getCompteEpargne();
        };
    }
}
