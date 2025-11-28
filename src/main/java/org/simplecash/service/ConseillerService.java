package org.simplecash.service;

import org.simplecash.entity.*;
import org.simplecash.repository.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConseillerService {

    private final ConseillerRepository conseillerRepository;
    private final AgenceRepository agenceRepository;
    private final GerantRepository gerantRepository;
    private final ClientRepository clientRepository;

    public ConseillerService(ConseillerRepository conseillerRepository,
                             AgenceRepository agenceRepository,
                             GerantRepository gerantRepository,
                             ClientRepository clientRepository) {

        this.conseillerRepository = conseillerRepository;
        this.agenceRepository = agenceRepository;
        this.gerantRepository = gerantRepository;
        this.clientRepository = clientRepository;
    }

    public Conseiller create(Conseiller conseiller) {
        return conseillerRepository.save(conseiller);
    }

    public Conseiller get(Long id) {
        return conseillerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Conseiller introuvable"));
    }

    public List<Conseiller> getAll() {
        return conseillerRepository.findAll();
    }

    public Conseiller update(Long id, Conseiller data) {
        Conseiller c = get(id);

        c.setNom(data.getNom());
        c.setPrenom(data.getPrenom());
        c.setEmail(data.getEmail());
        c.setTelephone(data.getTelephone());

        return conseillerRepository.save(c);
    }

    public void delete(Long id) {
        conseillerRepository.deleteById(id);
    }

    public Conseiller assignAgence(Long conseillerId, Long agenceId) {
        Conseiller c = get(conseillerId);
        Agence a = agenceRepository.findById(agenceId)
                .orElseThrow(() -> new RuntimeException("Agence introuvable"));

        c.setAgence(a);

        return conseillerRepository.save(c);
    }

    public Conseiller assignGerant(Long conseillerId, Long gerantId) {
        Conseiller c = get(conseillerId);
        Gerant g = gerantRepository.findById(gerantId)
                .orElseThrow(() -> new RuntimeException("Gérant introuvable"));

        c.setGerant(g);
        return conseillerRepository.save(c);
    }

    public Conseiller assignClient(Long conseillerId, Long clientId) {
        Conseiller c = get(conseillerId);

        Client client = clientRepository.findById(clientId)
                .orElseThrow(() -> new RuntimeException("Client introuvable"));

        client.setConseiller(c);
        c.getClients().add(client);

        clientRepository.save(client);
        return conseillerRepository.save(c);
    }

    public List<Client> getClients(Long conseillerId) {
        Conseiller c = get(conseillerId);
        return c.getClients();
    }
}
