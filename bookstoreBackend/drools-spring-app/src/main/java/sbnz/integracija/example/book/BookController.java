package sbnz.integracija.example.book;

import demo.facts.Book;
import org.springframework.web.bind.annotation.*;
import sbnz.integracija.example.book.dto.BookDisplayDTO;

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
}
