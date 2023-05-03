package sbnz.integracija.example.discount;

import demo.facts.Order;
import demo.facts.OrderItem;
import org.apache.maven.artifact.repository.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import sbnz.integracija.example.book.dto.OrderItemDTO;
import sbnz.integracija.example.book.dto.OrderWithUserIdDTO;
import sbnz.integracija.example.security.JwtUtil;

import java.util.ArrayList;
import java.util.List;


@RestController
public class OrderDiscountController {

    @Autowired
    private OrderDiscountService orderDiscountService;
    private JwtUtil jwtUtilService;

    @RequestMapping(path="api/orderItem")
    public ResponseEntity<OrderItem> getDiscount(@RequestBody OrderItem orderItem) {
        OrderItem discount = orderDiscountService.getDiscount(orderItem);
        return new ResponseEntity<>(discount, HttpStatus.OK);
    }

    @PostMapping("api/book/order")
    public ResponseEntity<Double> placeOrder(@RequestBody OrderWithUserIdDTO OrderWithUserIdDTO) {
        List<OrderItem> orderItems = new ArrayList<OrderItem>();
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
        orderDiscountService.getDiscount(order);

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

        order.setUserId(userId);
        orderDiscountService.create(order);

        return new ResponseEntity<>(price, HttpStatus.OK);
    }
}
