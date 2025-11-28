package org.simplecash.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.simplecash.entity.Conseiller;
import org.simplecash.service.ConseillerService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/conseillers")
@Tag(name = "Conseillers", description = "Gestion des conseillers bancaires")
public class ConseillerController {

    private final ConseillerService conseillerService;

    public ConseillerController(ConseillerService conseillerService) {
        this.conseillerService = conseillerService;
    }

    @Operation(summary = "Créer un conseiller", description = "Ajoute un nouveau conseiller dans le système.")
    @PostMapping
    public Conseiller create(@RequestBody Conseiller conseiller) {
        return conseillerService.create(conseiller);
    }

    @Operation(summary = "Récupérer un conseiller", description = "Retourne les informations d’un conseiller via son ID.")
    @GetMapping("/{id}")
    public Conseiller get(@PathVariable Long id) {
        return conseillerService.get(id);
    }

    @Operation(summary = "Lister tous les conseillers", description = "Retourne la liste complète des conseillers.")
    @GetMapping
    public List<Conseiller> getAll() {
        return conseillerService.getAll();
    }

    @Operation(summary = "Modifier un conseiller", description = "Met à jour les informations d’un conseiller.")
    @PutMapping("/{id}")
    public Conseiller update(@PathVariable Long id, @RequestBody Conseiller conseiller) {
        return conseillerService.update(id, conseiller);
    }

    @Operation(summary = "Supprimer un conseiller", description = "Supprime un conseiller du système.")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        conseillerService.delete(id);
    }

    @Operation(summary = "Attribuer une agence", description = "Associe un conseiller à une agence bancaire.")
    @PostMapping("/{id}/agence/{agenceId}")
    public Conseiller assignAgence(@PathVariable Long id, @PathVariable Long agenceId) {
        return conseillerService.assignAgence(id, agenceId);
    }

    @Operation(summary = "Attribuer un gérant", description = "Associe un conseiller à un gérant d’agence.")
    @PostMapping("/{id}/gerant/{gerantId}")
    public Conseiller assignGerant(@PathVariable Long id, @PathVariable Long gerantId) {
        return conseillerService.assignGerant(id, gerantId);
    }

    @Operation(summary = "Attribuer un client au conseiller", description = "Permet d’ajouter un client sous la responsabilité d’un conseiller.")
    @PostMapping("/{conseillerId}/clients/{clientId}")
    public Conseiller assignClientToConseiller(
            @PathVariable Long conseillerId,
            @PathVariable Long clientId
    ) {
        return conseillerService.assignClient(conseillerId, clientId);
    }
}
