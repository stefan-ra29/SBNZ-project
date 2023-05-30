package sbnz.integracija.example.user;

import demo.facts.Book;
import demo.facts.Rate;
import demo.facts.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import sbnz.integracija.example.exceptions.CustomBadRequestException;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository repository;

    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository repository, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public User create(User user) {
        if(repository.getUserByUsername(user.getUsername()) != null) {
            throw new CustomBadRequestException("Username already exists");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return repository.save(user);
    }

    @Override
    public User getUserByUsername(String username) {
        return repository.getUserByUsername(username);
    }



}
