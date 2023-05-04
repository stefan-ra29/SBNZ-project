package sbnz.integracija.example.book.dto;

import lombok.Getter;

import java.util.List;
@Getter
public class OrderWithUserIdDTO {
    private List<OrderItemDTO> books;
    private int userId;

}
