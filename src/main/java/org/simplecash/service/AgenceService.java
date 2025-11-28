package org.simplecash.service;

import org.simplecash.entity.Agence;
import org.simplecash.entity.Conseiller;
import org.simplecash.entity.Gerant;
import org.simplecash.repository.AgenceRepository;
import org.simplecash.repository.ConseillerRepository;
import org.simplecash.repository.GerantRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AgenceService {

    private final AgenceRepository agenceRepository;
    private final GerantRepository gerantRepository;
    private final ConseillerRepository conseillerRepository;

    public AgenceService(
            AgenceRepository agenceRepository,
            GerantRepository gerantRepository,
            ConseillerRepository conseillerRepository
    ) {
        this.agenceRepository = agenceRepository;
        this.gerantRepository = gerantRepository;
        this.conseillerRepository = conseillerRepository;
    }

    public Agence create(Agence agence) {
        return agenceRepository.save(agence);
    }

    public Agence get(Long id) {
        return agenceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Agence non trouvée : " + id));
    }

    public List<Agence> getAll() {
        return agenceRepository.findAll();
    }

    public Agence update(Long id, Agence updated) {
        Agence agence = get(id);
        agence.setNom(updated.getNom());
        agence.setAdresse(updated.getAdresse());

        return agenceRepository.save(agence);
    }

    public void delete(Long id) {
        agenceRepository.deleteById(id);
    }

    public Agence assignGerant(Long agenceId, Long gerantId) {
        Agence agence = get(agenceId);

        Gerant gerant = gerantRepository.findById(gerantId)
                .orElseThrow(() -> new RuntimeException("Gérant introuvable : " + gerantId));

        agence.setGerant(gerant);
        return agenceRepository.save(agence);
    }

    public Agence assignConseiller(Long agenceId, Long conseillerId) {
        Agence agence = get(agenceId);

        Conseiller conseiller = conseillerRepository.findById(conseillerId)
                .orElseThrow(() -> new RuntimeException("Conseiller introuvable : " + conseillerId));

        if (conseiller.getAgence() != null) {
            conseiller.getAgence().getConseillers().remove(conseiller);
        }

        conseiller.setAgence(agence);
        agence.getConseillers().add(conseiller);

        conseillerRepository.save(conseiller);
        return agenceRepository.save(agence);
    }

    public Agence removeConseiller(Long agenceId, Long conseillerId) {
        Agence agence = get(agenceId);

        Conseiller conseiller = conseillerRepository.findById(conseillerId)
                .orElseThrow(() -> new RuntimeException("Conseiller introuvable : " + conseillerId));

        if (!agence.getConseillers().contains(conseiller)) {
            throw new RuntimeException("Ce conseiller n'appartient pas à cette agence.");
        }

        agence.getConseillers().remove(conseiller);
        conseiller.setAgence(null);

        conseillerRepository.save(conseiller);
        return agenceRepository.save(agence);
    }
}
