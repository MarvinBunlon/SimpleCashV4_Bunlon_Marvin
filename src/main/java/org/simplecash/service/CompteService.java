package org.simplecash.service;

import org.simplecash.entity.Compte;
import org.simplecash.entity.CompteCourant;
import org.simplecash.entity.CompteEpargne;
import org.simplecash.repository.CompteRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
public class CompteService {

    private final CompteRepository compteRepository;

    public CompteService(CompteRepository compteRepository) {
        this.compteRepository = compteRepository;
    }

    @Transactional
    public Compte credit(Long compteId, BigDecimal montant) {
        Compte compte = compteRepository.findById(compteId)
                .orElseThrow(() -> new RuntimeException("Compte non trouvé"));

        compte.setSolde(compte.getSolde().add(montant));
        return compteRepository.save(compte);
    }

    @Transactional
    public Compte debit(Long compteId, BigDecimal montant) {
        Compte compte = compteRepository.findById(compteId)
                .orElseThrow(() -> new RuntimeException("Compte non trouvé"));

        BigDecimal nouveauSolde = compte.getSolde().subtract(montant);

        if (compte instanceof CompteCourant courant) {
            if (nouveauSolde.doubleValue() < -courant.getDecouvert()) {
                throw new RuntimeException("Découvert autorisé dépassé");
            }
        }

        if (compte instanceof CompteEpargne && nouveauSolde.doubleValue() < 0) {
            throw new RuntimeException("Un compte épargne ne peut pas être négatif");
        }

        compte.setSolde(nouveauSolde);
        return compteRepository.save(compte);
    }
}
