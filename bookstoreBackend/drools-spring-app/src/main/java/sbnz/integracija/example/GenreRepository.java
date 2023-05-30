package sbnz.integracija.example;

import demo.facts.Genre;
import demo.facts.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GenreRepository extends JpaRepository<Genre, Integer> {
    List<Genre> getAllByUsers(User user);
}
