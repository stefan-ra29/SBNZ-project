package sbnz.integracija.example.book;

import demo.facts.Book;
import demo.facts.BookRatingLevel;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sbnz.integracija.example.book.dto.BookDisplayDTO;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookServiceImpl implements BookService{

    private final BookRepository repository;
    private KieContainer kieContainer;

    public BookServiceImpl(BookRepository repository, KieContainer kieContainer) {
        this.repository = repository;
        this.kieContainer = kieContainer;
    }

    @Override
    public Book create(Book book) {
        return repository.save(book);
    }

    @Override
    public List<Book> getAll() {
        return repository.findAll();
    }
    @Override
    public Book getById(int id) {
        return repository.getById(id);
    }

    @Override
    public List<Book> getRecommendationsForUnauthorizedUsers() {

        KieSession kieSession = kieContainer.newKieSession();
        List<Book> allBooks = repository.findAll();
        for(Book book : allBooks) {
            kieSession.insert(book);
        }

        kieSession.getAgenda().getAgendaGroup("bookIsNew").setFocus();
        kieSession.fireAllRules();
        kieSession.getAgenda().getAgendaGroup("bookIsPopular").setFocus();
        kieSession.fireAllRules();
        System.out.println(allBooks);
        kieSession.getAgenda().getAgendaGroup("bookRating").setFocus();
        kieSession.fireAllRules();
        System.out.println(allBooks);
        kieSession.getAgenda().getAgendaGroup("bookRecommend").setFocus();
        kieSession.fireAllRules();

//        for(Book book : allBooks) {
//            if((book.isNew() == true && (book.getRatingLevel() == "NEUTRAL" || book.getRatingLevel() == "GOOD"))){
//                book.setRecommended(false);
//                System.out.println(book);
//            }
//        }
        kieSession.dispose();

        return null;
    }

}
