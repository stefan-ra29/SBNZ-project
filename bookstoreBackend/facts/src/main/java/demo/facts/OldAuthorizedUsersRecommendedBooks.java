package demo.facts;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
            recommendedBooks.put(book, 1L);
        }
    }

}