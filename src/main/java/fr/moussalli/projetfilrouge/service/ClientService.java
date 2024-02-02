package fr.moussalli.projetfilrouge.service;

import fr.moussalli.projetfilrouge.dto.ClientDTO;
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

    public Client convertToEntity(ClientDTO clientDTO) {
        Client client = new Client();
        client.setCompanyName(clientDTO.getCompanyName());
        client.setFirstName(clientDTO.getFirstName());
        client.setLastName(clientDTO.getLastName());
        client.setEmail(clientDTO.getEmail());
        client.setPhone(clientDTO.getPhone());
        client.setAddress(clientDTO.getAddress());
        client.setZipCode(clientDTO.getZipCode());
        client.setCity(clientDTO.getCity());
        client.setCountry(clientDTO.getCountry());
        // Supposons que State est un enum et que ClientDTO contient le libellé
        client.setState(ClientState.fromString(clientDTO.getState())); // Conversion basée sur l'énumération
        return client;
    }

    public ClientDTO convertToDTO(Client client) {
        ClientDTO clientDTO = new ClientDTO();
        clientDTO.setCompanyName(client.getCompanyName());
        clientDTO.setFirstName(client.getFirstName());
        clientDTO.setLastName(client.getLastName());
        clientDTO.setEmail(client.getEmail());
        clientDTO.setPhone(client.getPhone());
        clientDTO.setAddress(client.getAddress());
        clientDTO.setZipCode(client.getZipCode());
        clientDTO.setCity(client.getCity());
        clientDTO.setCountry(client.getCountry());
        clientDTO.setState(client.getState().toString()); // Assurez-vous que cette conversion est appropriée
        return clientDTO;
    }

}
