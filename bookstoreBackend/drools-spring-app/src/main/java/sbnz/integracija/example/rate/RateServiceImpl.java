package sbnz.integracija.example.rate;

import demo.facts.Book;
import demo.facts.Rate;
import demo.facts.User;
import org.springframework.stereotype.Service;
import sbnz.integracija.example.book.BookRepository;
import sbnz.integracija.example.user.UserRepository;

import java.util.List;

@Service
public class RateServiceImpl implements RateService {

    private final RateRepository repository;
    private final UserRepository userRepository;
    private final BookRepository bookRepository;

    public RateServiceImpl(RateRepository repository, UserRepository userRepository, BookRepository bookRepository) {
        this.repository = repository;
        this.userRepository = userRepository;
        this.bookRepository = bookRepository;
    }

    @Override
    public List<Rate> getAll(){
        return repository.findAll();
    }

    @Override
    public void rateBook(RateDto rateDto) {
        User user = userRepository.findById(rateDto.getUserId()).get();
        Book book = bookRepository.findById(rateDto.getBookId()).get();

        Rate rate = Rate.builder().user(user).book(book).rate(rateDto.getRate()).build();
        repository.save(rate);
    }

}
