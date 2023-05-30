package sbnz.integracija.example.book;

import demo.facts.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {

    Book getById(int id);
    @Query("select distinct author from books")
    List<String> getAllAuthors();
    List<Book> getAllByIdIn(List<Integer> ids);
}
