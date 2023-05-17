package sbnz.integracija.example.rate;

import demo.facts.Rate;
import org.springframework.stereotype.Service;
import sbnz.integracija.example.book.BookService;

import java.util.List;

@Service
public class RateServiceImpl implements RateService {

    private final RateRepository repository;

    public BookService bookService;

    public RateServiceImpl(RateRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Rate> getAll(){
        return repository.findAll();
    }

    @Override
    public void rateBook(Rate rate) { repository.save(rate); }

}
