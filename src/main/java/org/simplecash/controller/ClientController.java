package org.simplecash.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.simplecash.entity.Client;
import org.simplecash.service.ClientService;
import org.simplecash.service.CompteService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clients")
@Tag(name = "Clients", description = "Gestion des clients bancaires")
public class ClientController {

    private final ClientService clientService;
    private final CompteService compteService;

    public ClientController(ClientService clientService, CompteService compteService) {
        this.clientService = clientService;
        this.compteService = compteService;
    }

    @Operation(summary = "Créer un client", description = "Ajoute un nouveau client dans le système.")
    @PostMapping
    public Client createClient(@RequestBody Client client) {
        return clientService.create(client);
    }

    @Operation(summary = "Récupérer un client", description = "Retourne les informations d’un client via son ID.")
    @GetMapping("/{id}")
    public Client getClient(@PathVariable Long id) {
        return clientService.get(id);
    }

    @Operation(summary = "Lister tous les clients", description = "Retourne la liste complète des clients.")
    @GetMapping
    public List<Client> getAllClients() {
        return clientService.getAll();
    }

    @Operation(summary = "Modifier un client", description = "Met à jour les informations d’un client.")
    @PutMapping("/{id}")
    public Client updateClient(@PathVariable Long id, @RequestBody Client client) {
        return clientService.update(id, client);
    }

    @Operation(summary = "Supprimer un client", description = "Supprime un client du système.")
    @DeleteMapping("/{id}")
    public void deleteClient(@PathVariable Long id) {
        clientService.delete(id);
    }

    @Operation(summary = "Créer un compte pour un client", description = "Créer un compte courant (type=1) ou épargne (type=2).")
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

    @Operation(summary = "Lister les comptes d’un client", description = "Retourne le compte courant et le compte épargne du client.")
    @GetMapping("/{id}/comptes")
    public Object getClientAccounts(@PathVariable Long id) {
        Client client = clientService.get(id);

        return new Object() {
            public final Object compteCourant = client.getCompteCourant();
            public final Object compteEpargne = client.getCompteEpargne();
        };
    }
}
