package fr.moussalli.projetfilrouge.repository;

import fr.moussalli.projetfilrouge.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

}
