package sbnz.integracija.example.discount;

import demo.facts.Order;
import demo.facts.OrderItem;
import demo.facts.User;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sbnz.integracija.example.user.UserRepository;

@Service
public class OrderDiscountService {

    private static Logger log = LoggerFactory.getLogger(OrderDiscountService.class);

    @Autowired
    private KieContainer kieContainer;
    private  OrderRepository orderRepository;
    private OrderItemRepository    orderItemRepository;

    @Autowired
    public OrderDiscountService(KieContainer kieContainer, OrderRepository orderRepository, OrderItemRepository orderItemRepository) {
        log.info("Initialising a new example session.");
        this.kieContainer = kieContainer;
        this.orderRepository = orderRepository;
        this.orderItemRepository = orderItemRepository;
    }

    public Order create(Order order) {
        for(OrderItem orderItem : order.getItems()){
            orderItemRepository.save(orderItem);
        }
        return orderRepository.save(order);
    }
    public OrderItem getDiscount(OrderItem orderItem) {
//        OrderDiscount orderDiscount = new OrderDiscount();
        KieSession kieSession = kieContainer.newKieSession();
//        kieSession.setGlobal("orderDiscount",  new OrderDiscount());
        kieSession.insert(orderItem);
        kieSession.fireAllRules();
        kieSession.dispose();
        return orderItem;
    }

    public Order getDiscount(Order order) {



        KieSession kieSession = kieContainer.newKieSession();
        kieSession.insert(order);
        for (OrderItem orderItem: order.getItems()) {
            kieSession.insert(orderItem);
        }
        kieSession.fireAllRules();
        kieSession.dispose();
        return order;
    }

}
