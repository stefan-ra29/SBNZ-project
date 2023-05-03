package sbnz.integracija.example.discount;

import demo.facts.Order;
import demo.facts.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import sbnz.integracija.example.exceptions.CustomBadRequestException;
import sbnz.integracija.example.user.UserRepository;
import sbnz.integracija.example.user.UserService;

/*@Service
public class OrderServiceImpl implements OrderDiscountService {

    private final OrderRepository repository;

    private final PasswordEncoder passwordEncoder;

    public OrderServiceImpl(OrderRepository repository, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public Order create(Order order) {
        return repository.save(order);
    }


}*/
