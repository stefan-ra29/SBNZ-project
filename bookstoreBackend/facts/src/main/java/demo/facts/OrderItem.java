package demo.facts;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
//import sbnz.integracija.example.book.dto.OrderItemDTO;

import javax.persistence.*;
import java.awt.print.Book;

@Entity(name = "orderItems")
@Table(name = "orderItems")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private int bookId;
    private String category;
    private int amount;
    private double price;
    private double discountPrice = 0;
    private int discount = 0;
    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;
}


