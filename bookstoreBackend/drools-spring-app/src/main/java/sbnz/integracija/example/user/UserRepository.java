package sbnz.integracija.example.user;

import demo.facts.Genre;
import demo.facts.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    User getUserByUsername(String username);
    @Query("select genres from users u where u.id = ?1")
    List<Genre> getAllUserGenres(int userId);
}
