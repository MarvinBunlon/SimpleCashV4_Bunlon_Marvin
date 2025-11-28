package org.simplecash.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.simplecash.entity.Compte;
import org.simplecash.service.CompteService;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/comptes")
@Tag(name = "Comptes", description = "Opérations bancaires sur les comptes")
public class CompteController {

    private final CompteService compteService;

    public CompteController(CompteService compteService) {
        this.compteService = compteService;
    }

    @Operation(
            summary = "Créditer un compte",
            description = "Ajoute un montant au solde d'un compte via son ID."
    )
    @PostMapping("/{id}/credit")
    public Compte credit(@PathVariable Long id, @RequestParam BigDecimal montant) {
        return compteService.credit(id, montant);
    }

    @Operation(
            summary = "Débiter un compte",
            description = "Retire un montant du solde d'un compte via son ID."
    )
    @PostMapping("/{id}/debit")
    public Compte debit(@PathVariable Long id, @RequestParam BigDecimal montant) {
        return compteService.debit(id, montant);
    }

    @Operation(
            summary = "Effectuer un virement",
            description = "Transfère un montant d’un compte source vers un compte destination."
    )
    @PostMapping("/virement")
    public String virement(
            @RequestParam Long sourceId,
            @RequestParam Long destinationId,
            @RequestParam BigDecimal montant
    ) {
        compteService.virement(sourceId, destinationId, montant);
        return "Virement de " + montant + "€ effectué avec succès.";
    }
}
