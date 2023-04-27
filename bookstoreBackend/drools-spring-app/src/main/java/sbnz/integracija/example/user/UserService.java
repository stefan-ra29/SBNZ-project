package sbnz.integracija.example.user;

import demo.facts.User;

public interface UserService {
    User create(User user);
    User getUserByUsername(String username);
}
