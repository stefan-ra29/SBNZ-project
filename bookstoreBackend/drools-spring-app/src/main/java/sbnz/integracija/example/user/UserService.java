package sbnz.integracija.example.user;

import demo.facts.Book;
import demo.facts.User;

import java.util.List;

public interface UserService {
    User create(User user);
    User getUserByUsername(String username);
}
