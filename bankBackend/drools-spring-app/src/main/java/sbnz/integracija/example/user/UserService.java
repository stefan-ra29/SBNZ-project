package sbnz.integracija.example.user;

import demo.facts.User;
import sbnz.integracija.example.user.dto.UserCreateDTO;

public interface UserService {
    User create(UserCreateDTO userCreateDTO);
    User getUserByUsername(String username);
}
