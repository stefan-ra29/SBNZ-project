package sbnz.integracija.example.book;

import demo.facts.Book;

import java.util.List;

public interface BookService {
    Book create(Book book);
    List<Book> getAll();
    Book getById(int id);
}
