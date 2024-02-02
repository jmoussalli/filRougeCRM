package fr.moussalli.projetfilrouge.controller;

import fr.moussalli.projetfilrouge.model.Client;
import fr.moussalli.projetfilrouge.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @GetMapping("clients")
    public List<Client> getClients() {
        return clientService.getAll();
    }

    // POST /clients
    @PostMapping("clients")
    public ResponseEntity<?> addClient(@RequestBody Client client) {
        if (client.getFirstName() != null && client.getFirstName().isBlank())
            return ResponseEntity
                    .badRequest()
                    .body("le nom du client est obligatoire");
        else {
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(clientService.add(client));
        }
    }

    // GET /clients/4
    @GetMapping("clients/{id}")
    public ResponseEntity getById(@PathVariable("id") Long id) {
        Optional<Client> optional = clientService.findById(id);
        if (optional.isEmpty())
            return ResponseEntity.notFound().build();
        else {
            return ResponseEntity.ok(optional.get());
        }
    }

    // DELETE /clients/4
    @DeleteMapping("clients/{id}")
    public void delete(@PathVariable("id") Long id) {
        clientService.delete(id);
    }

    // PUT /clients/4
    @PutMapping("clients/{id}")
    public void update(@RequestBody Client client
            , @PathVariable("id") Integer id) {
        clientService.update(client);
    }


}
