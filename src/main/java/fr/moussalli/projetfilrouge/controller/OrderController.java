package fr.moussalli.projetfilrouge.controller;

import fr.moussalli.projetfilrouge.model.Order;
import fr.moussalli.projetfilrouge.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("orders")
    public List<Order> getOrders() {
        return orderService.getAll();
    }

    // POST /orders
    @PostMapping("orders")
    public ResponseEntity<?> addOrder(@RequestBody Order order) {
        if (order.getDesignation() != null && order.getDesignation().isBlank())
            return ResponseEntity
                    .badRequest()
                    .body("la désignation de la commande est obligatoire");
        else {
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(orderService.add(order));
        }
    }

    // GET /orders/4
    @GetMapping("orders/{id}")
    public ResponseEntity getById(@PathVariable("id") Long id) {
        Optional<Order> optional = orderService.findById(id);
        if (optional.isEmpty())
            return ResponseEntity.notFound().build();
        else {
            return ResponseEntity.ok(optional.get());
        }
    }

    // DELETE /orders/4
    @DeleteMapping("orders/{id}")
    public void delete(@PathVariable("id") Long id) {
        orderService.delete(id);
    }

    // PUT /orders/4
    @PutMapping("orders/{id}")
    public void update(@RequestBody Order order
            , @PathVariable("id") Integer id) {
        orderService.update(order);
    }


}
