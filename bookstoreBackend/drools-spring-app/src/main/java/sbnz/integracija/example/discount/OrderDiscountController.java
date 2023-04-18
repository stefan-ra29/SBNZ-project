package sbnz.integracija.example.discount;

import demo.facts.OrderItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sbnz.integracija.example.book.dto.OrderItemDTO;

import java.util.ArrayList;
import java.util.List;


@RestController
public class OrderDiscountController {

    @Autowired
    private OrderDiscountService orderDiscountService;

    @RequestMapping(path="api/orderItem")
    public ResponseEntity<OrderItem> getDiscount(@RequestBody OrderItem orderItem) {
        OrderItem discount = orderDiscountService.getDiscount(orderItem);
        return new ResponseEntity<>(discount, HttpStatus.OK);
    }

    @PostMapping("api/book/order")
    public ResponseEntity<Double> placeOrder(@RequestBody List<OrderItemDTO> order) {
        List<OrderItem> orderItems = new ArrayList<OrderItem>();
        double price = 0;
        for(OrderItemDTO orderItemDTO : order){
            OrderItem orderItem = new OrderItem();
            orderItem.setBookId(orderItemDTO.getId());
            orderItem.setAmount(orderItemDTO.getAmount());
            orderItem.setPrice(orderItemDTO.getPrice());
            orderItem.setCategory(orderItemDTO.getCategory());

            OrderItem discount = orderDiscountService.getDiscount(orderItem);
            orderItems.add(discount);

            if(discount.getDiscount() != 0){
                price += discount.getDiscountPrice();
            }
            else{
                price += discount.getPrice();
            }


        }
        return new ResponseEntity<>(price, HttpStatus.OK);
    }
}
