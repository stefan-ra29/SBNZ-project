package sbnz.integracija.example.book;

import demo.facts.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService{

    private final BookRepository repository;

    public BookServiceImpl(BookRepository repository) {
        this.repository = repository;
    }


    @Override
    public Book create(Book book) {
       return repository.save(book);
    }

    @Override
    public List<Book> getAll() {
        return repository.findAll();
    }
}
