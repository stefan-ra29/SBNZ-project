package sbnz.integracija.example.user;

import demo.facts.User;
import org.springframework.web.bind.annotation.*;
import sbnz.integracija.example.user.dto.UserCreateDTO;


@RestController
@RequestMapping(path="api/user")
public class UserController {

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @PostMapping
    public void addUser(@RequestBody UserCreateDTO userCreateDTO) {
        service.create(userCreateDTO);
    }

}
