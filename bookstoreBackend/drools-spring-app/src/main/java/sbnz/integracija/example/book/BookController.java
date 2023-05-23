package sbnz.integracija.example.book;

import demo.facts.Book;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import sbnz.integracija.example.book.dto.BookDisplayDTO;
import sbnz.integracija.example.book.dto.OrderItemDTO;

import javax.annotation.security.RolesAllowed;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path="api/book")
public class BookController {

    private final BookService service;

    public BookController(BookService service) {
        this.service = service;
    }

    @PostMapping
    public void addBook(@RequestBody Book book) {
        service.create(book);
    }




    @GetMapping
    public List<BookDisplayDTO> getAll() {
        List<BookDisplayDTO> bookDisplayDTOs = new ArrayList<>();
        for (Book book : service.getAll()) {
            BookDisplayDTO dto = new BookDisplayDTO(book);
            bookDisplayDTOs.add(dto);
        }

        return bookDisplayDTOs;
    }

    @GetMapping
    @RequestMapping("/recommendations/unauthorized")
    public List<Book> getRecommendationsForUnauthorizedUsers() {

        List<Book> recommendations = service.getRecommendationsForUnauthorizedUsers();

        return recommendations;
    }


}
