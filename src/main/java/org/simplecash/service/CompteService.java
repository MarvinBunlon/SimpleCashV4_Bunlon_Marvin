package org.simplecash.service;

import org.simplecash.entity.Client;
import org.simplecash.entity.Compte;
import org.simplecash.entity.CompteCourant;
import org.simplecash.entity.CompteEpargne;
import org.simplecash.repository.ClientRepository;
import org.simplecash.repository.CompteRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;

@Service
public class CompteService {

    private final CompteRepository compteRepository;
    private final ClientRepository clientRepository;

    public CompteService(CompteRepository compteRepository, ClientRepository clientRepository) {
        this.compteRepository = compteRepository;
        this.clientRepository = clientRepository;
    }

    // 🔵 Créer un compte courant
    @Transactional
    public CompteCourant createCompteCourant(Long clientId) {
        Client client = clientRepository.findById(clientId)
                .orElseThrow(() -> new RuntimeException("Client non trouvé"));

        CompteCourant compte = new CompteCourant();
        compte.setNumeroCompte("CC-" + System.currentTimeMillis());
        compte.setDateOuverture(LocalDate.now());
        compte.setSolde(BigDecimal.ZERO);
        compte.setClient(client);
        compte.setType("COURANT");

        return compteRepository.save(compte);
    }

    @Transactional
    public CompteEpargne createCompteEpargne(Long clientId) {
        Client client = clientRepository.findById(clientId)
                .orElseThrow(() -> new RuntimeException("Client non trouvé"));

        CompteEpargne compte = new CompteEpargne();
        compte.setNumeroCompte("CE-" + System.currentTimeMillis());
        compte.setDateOuverture(LocalDate.now());
        compte.setSolde(BigDecimal.ZERO);
        compte.setClient(client);
        compte.setType("EPARGNE");

        return compteRepository.save(compte);
    }

    // 🔵 Créditer un compte
    @Transactional
    public Compte credit(Long compteId, BigDecimal montant) {
        Compte compte = compteRepository.findById(compteId)
                .orElseThrow(() -> new RuntimeException("Compte non trouvé"));

        compte.setSolde(compte.getSolde().add(montant));
        return compteRepository.save(compte);
    }

    // 🔵 Débiter un compte
    @Transactional
    public Compte debit(Long compteId, BigDecimal montant) {
        Compte compte = compteRepository.findById(compteId)
                .orElseThrow(() -> new RuntimeException("Compte non trouvé"));

        BigDecimal nouveauSolde = compte.getSolde().subtract(montant);

        // Compte courant → autorisation découvert
        if (compte instanceof CompteCourant courant) {
            if (nouveauSolde.doubleValue() < -courant.getDecouvert()) {
                throw new RuntimeException("Découvert autorisé dépassé");
            }
        }

        // Compte épargne → jamais négatif
        if (compte instanceof CompteEpargne && nouveauSolde.doubleValue() < 0) {
            throw new RuntimeException("Un compte épargne ne peut pas être négatif");
        }

        compte.setSolde(nouveauSolde);
        return compteRepository.save(compte);
    }
}
