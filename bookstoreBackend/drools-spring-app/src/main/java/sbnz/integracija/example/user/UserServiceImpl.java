package sbnz.integracija.example.user;

import demo.facts.Book;
import demo.facts.User;
import org.springframework.stereotype.Service;
import sbnz.integracija.example.book.BookRepository;
import sbnz.integracija.example.book.BookService;

import java.util.List;

    @Service
    public class UserServiceImpl implements UserService {

        private final UserRepository repository;

        public UserServiceImpl(UserRepository repository) {
            this.repository = repository;
        }


        @Override
        public User create(User user) {
            return repository.save(user);
        }

    }
