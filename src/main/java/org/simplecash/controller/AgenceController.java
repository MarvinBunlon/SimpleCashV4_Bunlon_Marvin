package org.simplecash.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.simplecash.entity.Agence;
import org.simplecash.service.AgenceService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/agences")
@Tag(name = "Agences", description = "Gestion des agences bancaires, gérants et conseillers")
public class AgenceController {

    private final AgenceService agenceService;

    public AgenceController(AgenceService agenceService) {
        this.agenceService = agenceService;
    }

    @Operation(
            summary = "Créer une agence",
            description = "Permet de créer une nouvelle agence bancaire."
    )
    @PostMapping
    public Agence create(@RequestBody Agence agence) {
        return agenceService.create(agence);
    }

    @Operation(
            summary = "Récupérer une agence par ID",
            description = "Retourne les informations détaillées d'une agence."
    )
    @GetMapping("/{id}")
    public Agence get(@PathVariable Long id) {
        return agenceService.get(id);
    }

    @Operation(
            summary = "Lister toutes les agences",
            description = "Retourne la liste complète de toutes les agences."
    )
    @GetMapping
    public List<Agence> getAll() {
        return agenceService.getAll();
    }

    @Operation(
            summary = "Mettre à jour une agence",
            description = "Modifie les informations d'une agence existante."
    )
    @PutMapping("/{id}")
    public Agence update(@PathVariable Long id, @RequestBody Agence updated) {
        return agenceService.update(id, updated);
    }

    @Operation(
            summary = "Supprimer une agence",
            description = "Supprime une agence via son ID."
    )
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        agenceService.delete(id);
    }

    @Operation(
            summary = "Assigner un gérant à une agence",
            description = "Associe un gérant existant à une agence."
    )
    @PostMapping("/{agenceId}/gerant/{gerantId}")
    public Agence assignGerant(
            @PathVariable Long agenceId,
            @PathVariable Long gerantId
    ) {
        return agenceService.assignGerant(agenceId, gerantId);
    }

    @Operation(
            summary = "Assigner un conseiller à une agence",
            description = "Ajoute un conseiller dans une agence donnée."
    )
    @PostMapping("/{agenceId}/conseillers/{conseillerId}")
    public Agence assignConseiller(
            @PathVariable Long agenceId,
            @PathVariable Long conseillerId
    ) {
        return agenceService.assignConseiller(agenceId, conseillerId);
    }

    @Operation(
            summary = "Retirer un conseiller d'une agence",
            description = "Supprime le conseiller de la liste des conseillers d'une agence."
    )
    @DeleteMapping("/{agenceId}/conseillers/{conseillerId}")
    public Agence removeConseiller(
            @PathVariable Long agenceId,
            @PathVariable Long conseillerId
    ) {
        return agenceService.removeConseiller(agenceId, conseillerId);
    }
}
