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
import sbnz.integracija.example.book.dto.OrderItemDTO;
import sbnz.integracija.example.book.dto.OrderWithUserIdDTO;
import sbnz.integracija.example.user.UserRepository;

import java.util.ArrayList;
import java.util.List;

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
        Order savedOrder = orderRepository.save(order);

        for(OrderItem orderItem : order.getItems()) {
            orderItem.setOrder(order);
            orderItemRepository.save(orderItem);
        }

        return savedOrder;
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

    public double placeOrder(OrderWithUserIdDTO OrderWithUserIdDTO) {
        List<OrderItem> orderItems = new ArrayList<>();
        Order order = new Order();
        List<OrderItemDTO> OrderItemDTOs = OrderWithUserIdDTO.getBooks();
        int userId = OrderWithUserIdDTO.getUserId();
        double price = 0;
        for(OrderItemDTO orderItemDTO : OrderItemDTOs){
            OrderItem orderItem = new OrderItem();
            orderItem.setBookId(orderItemDTO.getId());
            orderItem.setAmount(orderItemDTO.getAmount());
            orderItem.setPrice(orderItemDTO.getPrice());
            orderItem.setCategory(orderItemDTO.getCategory());

            orderItems.add(orderItem);
            order.setOrderPrice(order.getOrderPrice() + orderItem.getPrice()*orderItem.getAmount());
        }

        order.setItems(orderItems);
        getDiscount(order);

        if(order.getDiscountedPrice() != 0){
            price = order.getDiscountedPrice();
        }
        else{
            for(OrderItem orderItem : order.getItems()){
                if(orderItem.getDiscountPrice() != 0){
                    price += orderItem.getDiscountPrice();
                }
                else{
                    price += orderItem.getPrice();
                }
            }
        }

        order.setDiscountedPrice(price);
        order.setUserId(userId);
        create(order);

        return price;
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
