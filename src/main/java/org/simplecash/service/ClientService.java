package org.simplecash.service;

import lombok.RequiredArgsConstructor;
import org.simplecash.entity.Client;
import org.simplecash.repository.ClientRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClientService {

    private final ClientRepository clientRepository;

    public Client create(Client client) {
        return clientRepository.save(client);
    }

    public Client get(Long id) {
        return clientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Client non trouvé"));
    }

    public List<Client> getAll() {
        return clientRepository.findAll();
    }

    public Client update(Long id, Client updated) {
        Client client = get(id);

        client.setNom(updated.getNom());
        client.setPrenom(updated.getPrenom());
        client.setAdresse(updated.getAdresse());
        client.setCodePostal(updated.getCodePostal());
        client.setVille(updated.getVille());
        client.setTelephone(updated.getTelephone());

        return clientRepository.save(client);
    }

    public void delete(Long id) {
        Client client = get(id);
        clientRepository.delete(client);
    }
}
