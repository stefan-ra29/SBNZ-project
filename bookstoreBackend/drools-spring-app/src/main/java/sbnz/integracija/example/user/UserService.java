package sbnz.integracija.example.user;

import demo.facts.Book;
import demo.facts.User;
import sbnz.integracija.example.user.dto.UserCreateDTO;

import java.util.List;

public interface UserService {
    User create(UserCreateDTO userCreateDTO);
    User getUserByUsername(String username);
}
