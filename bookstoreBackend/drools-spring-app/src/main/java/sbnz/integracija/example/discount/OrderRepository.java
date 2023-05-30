package sbnz.integracija.example.discount;

import demo.facts.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {
    List<Order> getAllByUserIdAndCreationTimeIsAfter(int userId, LocalDateTime creationTime);
}
