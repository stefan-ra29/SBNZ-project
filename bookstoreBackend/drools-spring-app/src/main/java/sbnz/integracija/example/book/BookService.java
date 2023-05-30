package sbnz.integracija.example.book;

import demo.facts.Book;
import demo.facts.User;

import java.util.List;

public interface BookService {
    Book create(Book book);
    List<Book> getAll();
    Book getById(int id);
    List<Book> getRecommendationsForUnauthorizedUsers();
    List<Book> getRecommendationsForAuthorizedUsers(int userId);

    List<Book> getBooksThatUserLikes(User user);
}
