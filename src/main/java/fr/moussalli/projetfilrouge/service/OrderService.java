package fr.moussalli.projetfilrouge.service;

import fr.moussalli.projetfilrouge.model.Order;
import fr.moussalli.projetfilrouge.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public Order add(Order a) {
        return orderRepository.save(a);
    }

    public List<Order> getAll() {
        return orderRepository.findAll();
    }

    public Optional<Order> findById(Long id) {
        return orderRepository.findById(id);
    }

    public void delete(Long id) {
        orderRepository.deleteById(id);
    }

    public void update(Order order) {
        orderRepository.save(order);
    }

}
