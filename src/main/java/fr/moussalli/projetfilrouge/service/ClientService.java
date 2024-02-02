package fr.moussalli.projetfilrouge.service;

import fr.moussalli.projetfilrouge.enums.ClientState;
import fr.moussalli.projetfilrouge.model.Client;
import fr.moussalli.projetfilrouge.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public Client add(Client a) {
        return clientRepository.save(a);
    }

    public List<Client> getAll() {
        return clientRepository.findAll();
    }

    public Optional<Client> findById(Long id) {
        return clientRepository.findById(id);
    }

    public void delete(Long id) {
        clientRepository.deleteById(id);
    }

    public void update(Client client) {
        clientRepository.save(client);
    }

}
