package org.simplecash.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.simplecash.entity.Gerant;
import org.simplecash.service.GerantService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/gerants")
@Tag(name = "Gérant", description = "Gestion des gérants d'agence")
public class GerantController {

    private final GerantService gerantService;

    public GerantController(GerantService gerantService) {
        this.gerantService = gerantService;
    }

    @Operation(summary = "Créer un gérant", description = "Ajoute un nouveau gérant dans le système.")
    @PostMapping
    public Gerant create(@RequestBody Gerant gerant) {
        return gerantService.create(gerant);
    }

    @Operation(summary = "Récupérer un gérant", description = "Retourne un gérant en fonction de son identifiant.")
    @GetMapping("/{id}")
    public Gerant get(@PathVariable Long id) {
        return gerantService.get(id);
    }

    @Operation(summary = "Lister tous les gérants", description = "Retourne la liste complète des gérants enregistrés.")
    @GetMapping
    public List<Gerant> getAll() {
        return gerantService.getAll();
    }

    @Operation(summary = "Modifier un gérant", description = "Met à jour les informations d’un gérant existant.")
    @PutMapping("/{id}")
    public Gerant update(@PathVariable Long id, @RequestBody Gerant updated) {
        return gerantService.update(id, updated);
    }

    @Operation(summary = "Supprimer un gérant", description = "Supprime définitivement un gérant du système.")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        gerantService.delete(id);
    }
}
