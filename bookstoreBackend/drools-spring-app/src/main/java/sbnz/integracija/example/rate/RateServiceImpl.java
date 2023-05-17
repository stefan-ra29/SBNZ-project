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
    public void rateBook(Rate rate) {
        for (Rate oneRate : getAll()) {
            if(oneRate.getBookId() == rate.getBookId() && oneRate.getUserId() == rate.getUserId()){
                throw new UnsupportedOperationException("You have already rated this book");
            }
            else{
                    if (rate.getRate() > 0 || rate.getRate() <= 5) {
                        repository.save(rate);
                    } else{
                        throw new UnsupportedOperationException("The rate must be a number between 1 and 5");
                    }

                }
            }


        }

}
