package sbnz.integracija.example.discount;

import demo.facts.Order;
import demo.facts.OrderItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sbnz.integracija.example.book.dto.OrderItemDTO;
import sbnz.integracija.example.book.dto.OrderWithUserIdDTO;
import sbnz.integracija.example.security.JwtUtil;

import javax.annotation.security.RolesAllowed;
import java.util.ArrayList;
import java.util.List;


@RestController
public class OrderDiscountController {

    @Autowired
    private OrderDiscountService orderDiscountService;

    @RolesAllowed("ROLE_BASIC")
    @PostMapping("api/book/order")
    public ResponseEntity<Double> placeOrder(@RequestBody OrderWithUserIdDTO orderWithUserIdDTO) {
        double price = orderDiscountService.placeOrder(orderWithUserIdDTO);

        return new ResponseEntity<>(price, HttpStatus.OK);
    }
}
