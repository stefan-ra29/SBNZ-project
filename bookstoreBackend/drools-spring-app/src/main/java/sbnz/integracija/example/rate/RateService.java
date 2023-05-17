package sbnz.integracija.example.rate;

import demo.facts.Book;
import demo.facts.Rate;

import java.util.List;

public interface RateService {

    void rateBook(Rate rate);

    List<Rate> getAll();

}
