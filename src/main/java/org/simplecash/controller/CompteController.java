package org.simplecash.controller;

import lombok.RequiredArgsConstructor;
import org.simplecash.entity.Compte;
import org.simplecash.service.CompteService;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/comptes")
@RequiredArgsConstructor
public class CompteController {

    private final CompteService compteService;

    public CompteController(CompteService compteService) {
        this.compteService = compteService;
    }

    @PostMapping("/{id}/credit")
    public Compte credit(@PathVariable Long id, @RequestParam BigDecimal montant) {
        return compteService.credit(id, montant);
    }

    @PostMapping("/{id}/debit")
    public Compte debit(@PathVariable Long id, @RequestParam BigDecimal montant) {
        return compteService.debit(id, montant);
    }
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
