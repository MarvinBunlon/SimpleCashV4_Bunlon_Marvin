package org.simplecash.service;

import org.simplecash.entity.Gerant;
import org.simplecash.repository.GerantRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GerantService {

    private final GerantRepository gerantRepository;

    public GerantService(GerantRepository gerantRepository) {
        this.gerantRepository = gerantRepository;
    }

    public Gerant create(Gerant gerant) {
        return gerantRepository.save(gerant);
    }

    public Gerant get(Long id) {
        return gerantRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Gérant non trouvé : " + id));
    }

    public List<Gerant> getAll() {
        return gerantRepository.findAll();
    }

    public Gerant update(Long id, Gerant updated) {
        Gerant gerant = get(id);

        gerant.setNom(updated.getNom());
        gerant.setPrenom(updated.getPrenom());
        gerant.setTelephone(updated.getTelephone());
        gerant.setEmail(updated.getEmail());

        return gerantRepository.save(gerant);
    }

    public void delete(Long id) {
        if (!gerantRepository.existsById(id)) {
            throw new RuntimeException("Gérant introuvable : " + id);
        }
        gerantRepository.deleteById(id);
    }
}
