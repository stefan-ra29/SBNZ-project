package sbnz.integracija.example.book.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemDTO {
    private int id;
    private String name;
    private String author;
    private int amount;
    private double price;
}
