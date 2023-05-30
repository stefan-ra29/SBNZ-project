package sbnz.integracija.example.book;

import demo.facts.Book;
import demo.facts.Order;
import demo.facts.OrderItem;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import sbnz.integracija.example.discount.OrderItemRepository;
import sbnz.integracija.example.discount.OrderRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
public class OrdersManager {
    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final BookRepository bookRepository;


    public List<Book> getUsersOrderedBooksInLast6Months(int userId) {

        List<Order> orders = orderRepository.getAllByUserIdAndCreationTimeIsAfter(userId,
                LocalDateTime.now().minusMonths(6));

        var ids = orders.stream().flatMap(o -> o.getItems().stream()).mapToInt(OrderItem::getBookId).boxed().collect(Collectors.toList());
        return bookRepository.getAllByIdIn(ids);
    }
}
