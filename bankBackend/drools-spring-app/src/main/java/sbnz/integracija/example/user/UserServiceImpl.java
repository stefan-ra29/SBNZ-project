package sbnz.integracija.example.user;

import demo.facts.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import sbnz.integracija.example.exceptions.CustomBadRequestException;
import sbnz.integracija.example.user.dto.UserCreateDTO;

import javax.persistence.EnumType;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository repository;

    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository repository, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public User create(UserCreateDTO userCreateDTO) {
        if(repository.getUserByUsername(userCreateDTO.getUsername()) != null) {
            throw new CustomBadRequestException("Username already exists");
        }
        User user = User.builder()
                .name(userCreateDTO.getName())
                .username(userCreateDTO.getUsername())
                .role(UserRole.CLIENT)
                .build();
        user.setPassword(passwordEncoder.encode(userCreateDTO.getPassword()));
        return repository.save(user);
    }

    @Override
    public User getUserByUsername(String username) {
        return repository.getUserByUsername(username);
    }



}
