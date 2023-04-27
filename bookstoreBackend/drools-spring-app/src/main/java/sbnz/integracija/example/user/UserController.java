package sbnz.integracija.example.user;

import demo.facts.Book;
import demo.facts.User;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(path="api/user")
public class UserController {

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @PostMapping
    public void addUser(@RequestBody User user) {
        service.create(user);
    }

}
