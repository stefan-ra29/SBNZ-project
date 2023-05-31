package demo.facts;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@AllArgsConstructor
@Getter
@Setter
public class OldAuthorizedUsersRecommendedBooks implements Serializable {

    private Map<Book, Long> recommendedBooks;

    private List<Book> booksUserLikes;

    public OldAuthorizedUsersRecommendedBooks() {
        this.recommendedBooks = new HashMap<>();
    }

    public void addBook(Book book){
        var bookFromMap = recommendedBooks.keySet().stream().filter(b -> b.getId() == book.getId()).findFirst();
        if(bookFromMap.isPresent()) {
            long value = recommendedBooks.get(bookFromMap.get());
            this.recommendedBooks.put(book, ++ value);
        } else {
            if(recommendedBooks.size() < 20){
                recommendedBooks.put(book, 1L);
            }
            else{
                Optional<Book> book2 = recommendedBooks.keySet().stream().filter(b -> !isBookNew(b) && recommendedBooks.get(b) == 1).findFirst();
                if(book2.isPresent()){
                    recommendedBooks.remove(book2.get());
                    recommendedBooks.put(book, 1L);
                }
            }

        }
    }

    private boolean isBookNew(Book book){
        if(book.getAddedToStoreDate().compareTo(LocalDate.now().minusMonths(1)) >= 0 || book.getPublishedDate().compareTo(LocalDate.now().minusMonths(6)) >= 0){
            return true;
        }
        else
            return false;
    }

}