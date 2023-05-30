package sbnz.integracija.example.book;

import demo.facts.*;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.stereotype.Service;
import sbnz.integracija.example.user.UserRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookServiceImpl implements BookService{

    private final BookRepository repository;
    private final UserRepository userRepository;
    private KieContainer kieContainer;

    public BookServiceImpl(BookRepository repository, UserRepository userRepository, KieContainer kieContainer) {
        this.repository = repository;
        this.userRepository = userRepository;
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
        UnauthorizedUsersRecommendedBook unauthorizedUsersRecommendedBook = new UnauthorizedUsersRecommendedBook();
        for(Book book : allBooks) {
            kieSession.insert(book);
            kieSession.insert(unauthorizedUsersRecommendedBook);
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
        kieSession.getAgenda().getAgendaGroup("removeBookRecommend").setFocus();
        kieSession.fireAllRules();
        kieSession.getAgenda().getAgendaGroup("removeRandomBookRecommend").setFocus();
        kieSession.fireAllRules();

        kieSession.dispose();

        List<Book> finalListOfRecommendedBooks = unauthorizedUsersRecommendedBook.getRecommendedBooks();
        return finalListOfRecommendedBooks;
    }

    @Override
    public List<Book> getRecommendationsForAuthorizedUsers(int userId) {
        KieSession kieSession = kieContainer.newKieSession();
        List<Book> allBooks = repository.findAll();
        User user = userRepository.findById(userId).orElseThrow();
        OldAuthorizedUsersRecommendedBooks books = new OldAuthorizedUsersRecommendedBooks();
        PiersonCorrelationHelper piersonCorrelationHelper =
                new PiersonCorrelationHelper(user, allBooks);
        UserLikedBooksHelper userLikedBooksHelper =
                new UserLikedBooksHelper(user, getBooksThatUserLikes(user));
        kieSession.insert(books);
        kieSession.insert(piersonCorrelationHelper);
        kieSession.insert(userLikedBooksHelper);
        for(Book book : allBooks) {
            kieSession.insert(book);
        }
        kieSession.getAgenda().getAgendaGroup("old-authorized-users").setFocus();
        kieSession.fireAllRules();

        return null;
    }

    @Override
    public List<Book> getBooksThatUserLikes(User user){
        List<Book> likedBooks = new ArrayList<>();
        for(Rate rate : user.getRates()){
            if(rate.getRate() >= 4){
                likedBooks.add(rate.getBook());
            }
        }
        return likedBooks;
    }

}
